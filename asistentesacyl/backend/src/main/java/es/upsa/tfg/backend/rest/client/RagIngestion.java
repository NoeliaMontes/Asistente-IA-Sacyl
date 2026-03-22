package es.upsa.tfg.backend.rest.client;

import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentSplitter;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import io.quarkiverse.langchain4j.pgvector.PgVectorEmbeddingStore;
import io.quarkus.logging.Log;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static dev.langchain4j.data.document.splitter.DocumentSplitters.recursive;

@Startup
@ApplicationScoped
public class RagIngestion {

    @Inject
    PgVectorEmbeddingStore store;

    @Inject
    EmbeddingModel embeddingModel;


    @PostConstruct
    void loadDocument() throws Exception {

        //Documentos NO-PDF (md, txt, etc.)
        //Valorar luego
        //List<Document> documents =
        //        FileSystemDocumentLoader.loadDocumentsRecursively("docs");

        //PDFs
        List<Document> pdfDocuments = Files.walk(Path.of("docs"))
                .filter(path -> path.toString().toLowerCase().endsWith(".pdf"))
                .map(path -> {
                    try {
                        Log.info("Cargando PDF: " + path);
                        return parseAsDocument(path);
                    } catch (IOException e) {
                        throw new RuntimeException("Error leyendo PDF " + path, e);
                    }
                })
                .toList();

        // Unir
        //documents = new java.util.ArrayList<>(documents);
        pdfDocuments = new java.util.ArrayList<>(pdfDocuments);
        //documents.addAll(pdfDocuments);


        //Splitter
        DocumentSplitter splitter = recursive(500, 0);

        //Ingestor
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingStore(store)
                .embeddingModel(embeddingModel)
                .documentSplitter(splitter)
                .build();

        //ingestor.ingest(documents);
        ingestor.ingest(pdfDocuments);
    }


    private Document parseAsDocument(Path pdfPath) throws IOException {
        try (PDDocument pdf = Loader.loadPDF(pdfPath.toFile())) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(pdf);
            text = text.replaceAll("\\s+", " ").trim();
            return Document.from(text);
        }
    }

}
