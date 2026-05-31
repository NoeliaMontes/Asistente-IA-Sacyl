package es.upsa.tfg.backend.application.ai.rag;

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

        //Leemos los documentos en formato pdf
        List<Document> pdfDocuments = Files.walk(Path.of("docs"))
                .filter(path -> path.toString().toLowerCase().endsWith(".pdf"))
                .map(path -> {
                    try {
                        //Obtenemos del path el nombre de la carpeta que es el id del usuario
                        Path relative = Path.of("docs").relativize(path);
                        String userId = relative.getName(0).toString();
                        Document document = parseAsDocument(path);
                        //Añadimos metadata para filtrar
                        document.metadata().put("id",userId);
                        return document;
                    } catch (IOException e) {
                        throw new RuntimeException("Error leyendo PDF " + path, e);
                    }
                })
                .toList();

        //Union
        pdfDocuments = new java.util.ArrayList<>(pdfDocuments);

        //Splitter
        DocumentSplitter splitter = recursive(500, 0);

        //Ingestor
        EmbeddingStoreIngestor ingestor = EmbeddingStoreIngestor.builder()
                .embeddingStore(store)
                .embeddingModel(embeddingModel)
                .documentSplitter(splitter)
                .build();
        ingestor.ingest(pdfDocuments);
    }


    //Parseamos de pdf a documento para que lo pueda leer sin problema
    private Document parseAsDocument(Path pdfPath) throws IOException {
        try (PDDocument pdf = Loader.loadPDF(pdfPath.toFile())) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(pdf);
            text = text.replaceAll("\\s+", " ").trim();
            return Document.from(text);
        }
    }

}
