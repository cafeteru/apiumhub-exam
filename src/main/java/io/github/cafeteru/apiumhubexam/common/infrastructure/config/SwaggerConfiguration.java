package io.github.cafeteru.apiumhubexam.common.infrastructure.config;

import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition(servers = {
    @Server(url = "/"),
})
public class SwaggerConfiguration {

    @Bean
    @Primary
    public LinkDiscoverers discoverers() {
        final List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.of(plugins));
    }

    @Bean
    public GroupedOpenApi groupFactory() {
        return GroupedOpenApi.builder()
            .group("default")
            .addOpenApiCustomiser(apiCustomizer())
            .build();
    }

    private OpenApiCustomiser apiCustomizer() {
        return openApi -> openApi.info(
            new Info().title("Apiumhub Exam")
                .description("Apiumhub Exam Backend Skills")
                .version("1.0")
                .contact(new Contact().email("cafeteru.dev@gmail.com"))
        );
    }

}
