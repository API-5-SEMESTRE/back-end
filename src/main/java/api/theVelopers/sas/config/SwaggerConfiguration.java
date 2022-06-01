package api.theVelopers.sas.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("api.theVelopers.sas.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("SAS - Score Analysis System")
                .description("Documentando as APIs REST do sistema")
                .version("1.0.0")
                .license("Open Source Initiative - OSI")
                .licenseUrl("https://opensource.org/")
                .contact(new Contact("SAS", "https://score-analysis-system-front.herokuapp.com/#/", "score.analysis.sytem@gmail.com"))
                .build();
    }

}
