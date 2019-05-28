package com.chen.swagger.config;

import io.github.robwin.markup.builder.MarkupLanguage;
import io.github.robwin.swagger2markup.GroupBy;
import io.github.robwin.swagger2markup.Swagger2MarkupConverter;
import org.junit.Test;

/**
 * use package create swagger.json
 */
public class GenApiDoc {
    private String snippetDir = "/generated/snippets";
    private String outputDir = "generated/swagger-ui";

    // two: create paths.md/definitions.md/overview.md

    @Test
    public void doIt() throws Exception {
        // 读取上一步生成的swagger.json转成asciiDoc,写入到outputDir
        // 这个outputDir必须和插件里面<generated></generated>标签配置一致
        Swagger2MarkupConverter.from(outputDir + "/swagger.json")
                .withPathsGroupedBy(GroupBy.TAGS)// 按tag排序
                .withMarkupLanguage(MarkupLanguage.MARKDOWN)// 格式
                .withExamples(snippetDir)
                .build()
                .intoFolder(outputDir);// 输出
    }
}
