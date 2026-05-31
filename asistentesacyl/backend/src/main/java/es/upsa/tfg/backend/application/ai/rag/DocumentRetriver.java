package es.upsa.tfg.backend.application.ai.rag;

import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.AugmentationRequest;
import dev.langchain4j.rag.AugmentationResult;
import dev.langchain4j.rag.DefaultRetrievalAugmentor;
import dev.langchain4j.rag.RetrievalAugmentor;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import es.upsa.tfg.backend.application.utils.UserContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class DocumentRetriver implements RetrievalAugmentor
{
    @Inject
    UserContext context;

    @Inject
    RetriverCache cache;

    private final EmbeddingStore store;
    private final EmbeddingModel model;

    DocumentRetriver(EmbeddingStore store, EmbeddingModel model)
    {
        this.store = store;
        this.model = model;
    }


    //Creamos el augmentor en cada pregunta para asegurarnos de filtrar
    @Override
    public AugmentationResult augment(AugmentationRequest augmentationRequest)
    {
        //Obtenermos el id de usuario
        String userId = context.getUserId();

        //Generamos el embedding store o lo obtenemos del caché
        EmbeddingStoreContentRetriever contentRetriever = cache.get(userId, model, store);

        //Creamos el augmentor con el content retriver adecuado
        RetrievalAugmentor augmentor = DefaultRetrievalAugmentor.builder()
                .contentRetriever(contentRetriever)
                .build();

        //Devolvemos el resultado de ejecutar el augmentor, es decir los documentos encontrados
        return augmentor.augment(augmentationRequest);
    }
}

