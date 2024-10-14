package com.example.springboot.model;

import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.example.springboot.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

/**
 * Room
 */
@Validated
@NotUndefined
public class Room {
	@JsonProperty("id")

	private Long id = null;

	@JsonProperty("name")

	@JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
	@JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
	private String name = null;

	@JsonProperty("capacity")

	@JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
	@JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
	private Integer capacity = null;

	@JsonProperty("isIMax")

	@JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
	@JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
	private Boolean isIMax = false;

	public Room id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 **/
	@NotNull
	@Schema(example = "10", required = true, description = "")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Room name(String name) {
		this.name = name;
		return this;
	}

	/**
	 * Room's name
	 * 
	 * @return name
	 **/
	@Schema(example = "Sala 1", description = "Room's name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Room capacity(Integer capacity) {
		this.capacity = capacity;
		return this;
	}

	/**
	 * Get capacity
	 * 
	 * @return capacity
	 **/
	@Schema(example = "7", description = "")
	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Room isIMax(Boolean isIMax) {
		this.isIMax = isIMax;
		return this;
	}

	/**
	 * Get isIMax
	 * 
	 * @return isIMax
	 **/
	@Schema(description = "")
	public Boolean isIsIMax() {
		return isIMax;
	}

	public void setIsIMax(Boolean isIMax) {
		this.isIMax = isIMax;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Room room = (Room) o;
		return Objects.equals(this.id, room.id) && Objects.equals(this.name, room.name)
				&& Objects.equals(this.capacity, room.capacity) && Objects.equals(this.isIMax, room.isIMax);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, capacity, isIMax);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Room {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    name: ").append(toIndentedString(name)).append("\n");
		sb.append("    capacity: ").append(toIndentedString(capacity)).append("\n");
		sb.append("    isIMax: ").append(toIndentedString(isIMax)).append("\n");
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
