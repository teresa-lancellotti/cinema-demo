/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.62).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package com.example.springboot.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.springboot.model.Show;
import com.example.springboot.model.ShowsFound;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

//@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-10T13:33:40.183519972Z[GMT]")
@Validated
public interface ShowApi {

    @Operation(summary = "Find cinema shows by dates (from - to)", description = "Return list of shows scheduled in the specified time frame", tags={ "show" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Show.class)))),
        @ApiResponse(responseCode = "400", description = "Invalid date (from or to) supplied"),
        @ApiResponse(responseCode = "404", description = "Shows not found") })
	@GetMapping(value = "/show/findByFromTo", produces = { "application/json", "application/xml" })
    ResponseEntity<ShowsFound> getShowsByFromTo(@NotNull @Parameter(in = ParameterIn.QUERY, description = "Search shows from this date (Format yyyy-MM-dd)", required=true,schema=@Schema()) @Valid @RequestParam(required = true) String showDateFrom
    											, @Parameter(in = ParameterIn.QUERY, description = "Search shows until this date ()Format yyyy-MM-dd)" ,schema=@Schema()) @Valid @RequestParam(required = false) String showDateTo);


    @Operation(summary = "Find cinema shows scheduled until yesterday", description = "Return list of shows scheduled until yesterday", tags={ "show" })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "successful operation", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShowsFound.class))),
        @ApiResponse(responseCode = "400", description = "Invalid research supplied"),
        @ApiResponse(responseCode = "404", description = "Shows not found") })
	@GetMapping(value = "/show/findOld", produces = { "application/json", "application/xml" })
    ResponseEntity<ShowsFound> getOldShows();

}

