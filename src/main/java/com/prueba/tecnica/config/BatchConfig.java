package com.prueba.tecnica.config;

import com.prueba.tecnica.model.Factura;
import com.prueba.tecnica.processor.FacturaProcessor;
import com.prueba.tecnica.reader.FacturaItemReader;
import com.prueba.tecnica.repository.FacturaRepository;
import com.prueba.tecnica.writer.FacturaItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;

@Configuration
public class BatchConfig {

    private final PlatformTransactionManager transactionManager;
    private final FacturaRepository facturaRepository;
    private final JobLauncher jobLauncher;

    public BatchConfig(PlatformTransactionManager transactionManager, FacturaRepository facturaRepository, JobLauncher jobLauncher) {
        this.transactionManager = transactionManager;
        this.facturaRepository = facturaRepository;
        this.jobLauncher = jobLauncher;
    }

    // Definimos el Job que se ejecutará al arrancar
    @Bean
    public Job extraccionFacturasJob(JobRepository jobRepository) {
        return new JobBuilder("extraccionFacturasJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(step1(jobRepository))
                .build();
    }

    // Definimos el primer paso del Job
    @Bean
    public Step step1(JobRepository jobRepository) {
        return new StepBuilder("step1", jobRepository)
                .<Factura, Factura>chunk(10, transactionManager)
                .reader(itemReader(null)) // Lector para leer las facturas de Oracle
                .processor(new FacturaProcessor()) // Procesador si es necesario
                .writer(itemWriter()) // Escribirá en el archivo CSV
                .build();
    }


    @Bean
    @StepScope
    public FacturaItemReader itemReader(
            @Value("#{jobParameters['fecha']}") String fechaStr) {
        LocalDate fecha = LocalDate.parse(fechaStr);
        return new FacturaItemReader(facturaRepository, fecha);
    }

    // Escritor de facturas (CSV)
    @Bean
    public FacturaItemWriter itemWriter() {
        return new FacturaItemWriter(); // Escribirá las facturas extraídas en el archivo CSV
    }

    /**
     * Método que obtiene la fecha desde los parámetros del Job
     */
    private LocalDate getFechaFromJobParameters() {
        // Obtener el parámetro de fecha desde los parámetros del Job
        String fechaParam = System.getProperty("fecha");
        return fechaParam != null ? LocalDate.parse(fechaParam) : LocalDate.now();
    }

    @Bean
    public CommandLineRunner runJob(Job job) {
        return args -> {
            // Si no hay parámetro "fecha", no ejecutar el job
            String fechaParam = getFechaParam(args);
            if (fechaParam == null) {
                System.out.println("No se proporcionó el parámetro --fecha, el job no se ejecutará.");
                return;
            }

            LocalDate fecha = LocalDate.parse(fechaParam);

            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("fecha", fecha.toString())
                    .addLong("run.id", System.currentTimeMillis())
                    .toJobParameters();

            jobLauncher.run(job, jobParameters);
        };
    }

    /**
     * Método que obtiene el parámetro de fecha de los argumentos de la línea de comandos.
     */
    private String getFechaParam(String[] args) {
        // Busca el parámetro "fecha" en los argumentos de la línea de comandos
        for (String arg : args) {
            if (arg.startsWith("--fecha=")) {
                return arg.split("=")[1]; // Devuelve la fecha si está presente
            }
        }
        return null; // Si no está presente, devuelve null
    }
}
