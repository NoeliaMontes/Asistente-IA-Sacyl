package es.upsa.tfg.backend.application.ai.rag;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

@ApplicationScoped
public class RetriverCache
{
    @Inject
    @ConfigProperty(name = "model.results")
    Integer results;

    @Inject
    @ConfigProperty(name = "model.score")
    Double score;

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
                        //Máximo de resultados obtenidos
                        .maxResults(results)
                        //Indica el minimo de parecido que deben tener para que los acepte
                        .minScore(score)
                        .filter(metadataKey("id").isEqualTo(id))
                        .build());
    }


}
