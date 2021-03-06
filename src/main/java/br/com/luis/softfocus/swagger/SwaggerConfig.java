package br.com.luis.softfocus.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

	@Value("${swagger.enabled}")
	private boolean swaggerEnabled;

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.com.luis.softfocus.controllerapi"))
				.paths(PathSelectors.any()).build().apiInfo(getAppInfo()).enable(swaggerEnabled);
	}

	private ApiInfo getAppInfo() {

		return new ApiInfoBuilder().title("SOFTFOCUS - PROAGRO - API").description("API ProAgro").version("1.0")
				.build();
	}
}
