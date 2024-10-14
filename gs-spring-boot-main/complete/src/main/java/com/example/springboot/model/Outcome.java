package com.example.springboot.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.example.springboot.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Outcome of invocations to services. Useful for customized messages to the
 * client
 */
@Schema(description = "Outcome of invocations to services. Useful for customized messages to the client")
@Validated
@NotUndefined
//@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-10T13:33:40.183519972Z[GMT]")

public class Outcome {
	@JsonProperty("code")
	private OutcomeCode code = null;

	@JsonProperty("description")
	private String description = null;

	public Outcome code(OutcomeCode code) {
		this.code = code;
		return this;
	}

	/**
	 * Get code
	 * 
	 * @return code
	 **/
	@Schema(required = true, description = "")
	@Valid
	@NotNull
	public OutcomeCode getCode() {
		return code;
	}

	public void setCode(OutcomeCode code) {
		this.code = code;
	}

	public Outcome description(String description) {
		this.description = description;
		return this;
	}

	/**
	 * Operation outcome description. Useful for customized messages to the client
	 * 
	 * @return description
	 **/

	@Schema(example = "From date is mandatory or operation successfully completed", required = true, description = "Operation outcome description. Useful for customized messages to the client")

	@NotNull
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Outcome outcome = (Outcome) o;
		return Objects.equals(this.code, outcome.code) && Objects.equals(this.description, outcome.description);
	}

	@Override
	public int hashCode() {
		return Objects.hash(code, description);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Outcome {\n");

		sb.append("    code: ").append(toIndentedString(code)).append("\n");
		sb.append("    description: ").append(toIndentedString(description)).append("\n");
		sb.append("}");
		return sb.toString();
	}

	/**
	 * Convert the given object to string with each line indented by 4 spaces
	 * (except the first line).
	 */
	private String toIndentedString(java.lang.Object o) {
		if (o == null) {
			return "null";
		}
		return o.toString().replace("\n", "\n    ");
	}
}
