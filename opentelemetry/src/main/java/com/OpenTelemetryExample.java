package com;

import io.opentelemetry.api.GlobalOpenTelemetry;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.resources.Resource;
import io.opentelemetry.sdk.trace.SdkTracerProvider;
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor;
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter;
import io.opentelemetry.semconv.ResourceAttributes;

public class OpenTelemetryExample {

    public static OpenTelemetry instance = null;
    // 初始化 OpenTelemetry
    public static OpenTelemetry initOpenTelemetry() {
        if (instance!=null) {
            return instance;
        }
        // 配置 OTLP 导出器，默认会发送到 localhost:4317
        OtlpGrpcSpanExporter otlpExporter = OtlpGrpcSpanExporter.builder()
                .setEndpoint("http://ruijie.asia:4317") // 设置你的 Collector 的地址
                .build();

        // 创建一个 BatchSpanProcessor
        BatchSpanProcessor spanProcessor = BatchSpanProcessor.builder(otlpExporter)
                .build();

        Resource resource = Resource.getDefault()
                .merge(Resource.create(Attributes.of(
                        ResourceAttributes.SERVICE_NAME, "serviceNamezzz",
                        ResourceAttributes.TELEMETRY_SDK_LANGUAGE, "java")));

        // 创建 Tracer Provider 并添加 Span Processor
        SdkTracerProvider tracerProvider = SdkTracerProvider.builder()
                .addSpanProcessor(spanProcessor)
                .setResource(resource)
                .build();

        // 创建 OpenTelemetry 实例
        OpenTelemetrySdk openTelemetry = OpenTelemetrySdk.builder()
                .setTracerProvider(tracerProvider)
                .buildAndRegisterGlobal();
        instance = openTelemetry;
        return openTelemetry;
    }

    public static void main(String[] args) {
        // 初始化 OpenTelemetry
        OpenTelemetry openTelemetry = initOpenTelemetry();

        // 获取一个 Tracer
        Tracer tracer = openTelemetry.getTracer("example-tracer");

        // 创建一个 Span
        Span span = tracer.spanBuilder("example-span").startSpan();

        // 添加属性
        span.setAttribute("example-attribute", "valuezzzzz" + System.currentTimeMillis());

        // 模拟一些操作
        doSomeWork();

        // 结束 Span
        span.end();

        // 确保所有 Span 数据都已发送到 Collector
        // OpenTelemetrySdk.getGlobalTracerManagement().shutdown();
        try {
            Thread.sleep(30000); // 模拟 1 秒的工作
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("finish");
    }

    // 模拟工作的方法
    private static void doSomeWork() {
        try {
            Thread.sleep(1000); // 模拟 1 秒的工作
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
