package es.upsa.tfg.backend.application.ai.rag;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

@ApplicationScoped
public class RetriverCache
{

    //ConcurrentHashMap en caso de varias solicitudes al mismo tiempo
    static Map<String, EmbeddingStoreContentRetriever> cache = new ConcurrentHashMap<>();

    public EmbeddingStoreContentRetriever get(String userId, EmbeddingModel model, EmbeddingStore store)
    {
        //Compute if absent revisa si en cache se encuentra la key userId
        //En caso de que no esté ejecutá el código (en este caso la creación de un nuevo embedding store)
        return cache.computeIfAbsent(userId, id ->
                EmbeddingStoreContentRetriever.builder()
                        .embeddingModel(model)
                        .embeddingStore(store)
                        .maxResults(3)
                        .filter(metadataKey("id").isEqualTo(id))
                        .build());
    }


}
