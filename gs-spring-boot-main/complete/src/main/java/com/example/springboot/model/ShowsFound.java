package com.example.springboot.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.example.springboot.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * List of shows found and outcome of the operation.
 */
@Schema(description = "List of shows found and outcome of the operation.")
@Validated
@NotUndefined
//@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2024-10-10T13:33:40.183519972Z[GMT]")

public class ShowsFound {
	@JsonProperty("showList")
	@Valid
	private List<Show> showList = null;
	@JsonProperty("outcome")

	private Outcome outcome = null;

	public ShowsFound showList(List<Show> showList) {
		this.showList = showList;
		return this;
	}

	public ShowsFound addShowListItem(Show showListItem) {
		if (this.showList == null) {
			this.showList = new ArrayList<Show>();
		}
		this.showList.add(showListItem);
		return this;
	}

	/**
	 * List of shows found
	 * 
	 * @return showList
	 **/
	@Schema(description = "List of shows found")
	@Valid
	public List<Show> getShowList() {
		return showList;
	}

	public void setShowList(List<Show> showList) {
		this.showList = showList;
	}

	public ShowsFound outcome(Outcome outcome) {
		this.outcome = outcome;
		return this;
	}

	/**
	 * Get outcome
	 * 
	 * @return outcome
	 **/
	@Schema(required = true, description = "")
	@Valid
	@NotNull
	public Outcome getOutcome() {
		return outcome;
	}

	public void setOutcome(Outcome outcome) {
		this.outcome = outcome;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ShowsFound showsFound = (ShowsFound) o;
		return Objects.equals(this.showList, showsFound.showList) && Objects.equals(this.outcome, showsFound.outcome);
	}

	@Override
	public int hashCode() {
		return Objects.hash(showList, outcome);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class ShowsFound {\n");

		sb.append("    showList: ").append(toIndentedString(showList)).append("\n");
		sb.append("    outcome: ").append(toIndentedString(outcome)).append("\n");
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
