package com;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {


    // http://127.0.0.1:8080/test
    @RequestMapping(value = "/test")
    public String index() {
        return "Hello World spring-boot-study-demo!!!";
    }


    // http://127.0.0.1:8080/test2
    @RequestMapping(value = "/test2")
    public String test2() {
        // 创建一个 Span
        OpenTelemetry openTelemetry = OpenTelemetryExample.initOpenTelemetry();
        Tracer tracer = openTelemetry.getTracer("example-tracerz");
        Span span = tracer.spanBuilder("hello-span").startSpan();
        try {
            // 在 Span 中记录数据
            span.setAttribute("example-attribute", "valuezzzzz" + System.currentTimeMillis());
            span.addEvent("Processing /hello request");

            // 模拟一些业务逻辑
            return "Hello, OpenTelemetry!";
        } finally {
            // 结束 Span
            span.end();
        }
    }

}
