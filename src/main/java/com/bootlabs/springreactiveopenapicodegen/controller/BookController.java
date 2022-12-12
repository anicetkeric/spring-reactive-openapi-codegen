package com.bootlabs.springreactiveopenapicodegen.controller;


import com.bootlabs.springreactiveopenapicodegen.api.v1.BookApi;
import com.bootlabs.springreactiveopenapicodegen.dto.BookDTO;
import com.bootlabs.springreactiveopenapicodegen.dto.SuccessResponseDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.Collections;

/**
 * REST controller for managing {@link com.bootlabs.springreactiveopenapicodegen.dto.BookDTO}.
 */
@Validated
@Tag(name = "Book", description = "Book controller")
@RestController
@RequestMapping("/api/v1")
public class BookController implements BookApi {

    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<ResponseEntity<SuccessResponseDTO>> _createBook(
            @Parameter(name = "BookDTO", description = "", required = true, schema = @Schema(description = "")) @Valid @RequestBody Mono<BookDTO> bookDTO,
            @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        return bookDTO
                .map(b -> ResponseEntity.created(URI.create("/api/v1/book/" + b.getId()))
                        .body(new SuccessResponseDTO(b, "book created Successfully")));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<ResponseEntity<SuccessResponseDTO>> _deleteBook(
            @Parameter(name = "id", description = "", required = true, schema = @Schema(description = "")) @PathVariable("id") String id,
            @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        return Mono.just(ResponseEntity
                .ok()
                .body(new SuccessResponseDTO(null, "Book deleted Successfully")));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<ResponseEntity<SuccessResponseDTO>> _getAllBook(
            @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
       return Mono.just(ResponseEntity
                .ok()
                .body(new SuccessResponseDTO(Collections.emptyList(), "result found")));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<ResponseEntity<SuccessResponseDTO>> _getOneBook(
            @Parameter(name = "id", description = "", required = true, schema = @Schema(description = "")) @PathVariable("id") String id,
            @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        return Mono.just(ResponseEntity
                .ok()
                .body(new SuccessResponseDTO(BookDTO.builder().id(id).build(), "result found")));
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Mono<ResponseEntity<SuccessResponseDTO>> _updateBook(
            @Parameter(name = "id", description = "", required = true, schema = @Schema(description = "")) @PathVariable("id") String id,
            @Parameter(name = "BookDTO", description = "", required = true, schema = @Schema(description = "")) @Valid @RequestBody Mono<BookDTO> bookDTO,
            @Parameter(hidden = true) final ServerWebExchange exchange
    ) {
        return updateBook(id, bookDTO, exchange);
    }

}
