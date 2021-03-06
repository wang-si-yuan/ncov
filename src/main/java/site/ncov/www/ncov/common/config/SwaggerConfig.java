package site.ncov.www.ncov.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import site.ncov.www.ncov.common.utils.DataFactory;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket commonApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("commonApi")
                .host(DataFactory.site)
                .apiInfo(commonApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("site.ncov.www.ncov.common"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket placeApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("placeAPI")
                .host(DataFactory.site)
                .apiInfo(placeApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("site.ncov.www.ncov.place"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public Docket vaccinationApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("vaccinationAPI")
                .host(DataFactory.site)
                .apiInfo(vaccinationApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("site.ncov.www.ncov.vaccination"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo vaccinationApiInfo() {
        return new ApiInfoBuilder()
                .title("疫苗模块文档")
                .description("疫苗模块api定义")
                .version("1.0")
                .build();
    }

    private ApiInfo commonApiInfo(){

        return new ApiInfoBuilder()
                .title("公共模块文档")
                .description("公共模块api定义")
                .version("1.0")
                .build();
    }

    private ApiInfo placeApiInfo(){

        return new ApiInfoBuilder()
                .title("地点模块文档")
                .description("地点模块api定义")
                .version("1.0")
                .build();
    }
}
