package com.example.springboot.model;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.validation.annotation.Validated;

import com.example.springboot.configuration.NotUndefined;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

/**
 * Show
 */
@Validated
@NotUndefined

public class Show {
	@JsonProperty("id")

	private Long id = null;

	@JsonProperty("title")

	@JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
	@JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
	private String title = null;

	@JsonProperty("time")

	@JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
	@JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
	private String time = null;

	@JsonProperty("from")

	@JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
	@JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
	private LocalDate from = null;

	@JsonProperty("to")

	@JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
	@JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
	private LocalDate to = null;

	@JsonProperty("duration")

	@JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
	@JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
	private Long duration = 60l;

	@JsonProperty("idRoom")

	@JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
	@JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
	private Long idRoom = null;

	@JsonProperty("room")

	@JsonInclude(JsonInclude.Include.NON_ABSENT) // Exclude from JSON if absent
	@JsonSetter(nulls = Nulls.FAIL) // FAIL setting if the value is null
	private Room room = null;

	public Show id(Long id) {
		this.id = id;
		return this;
	}

	/**
	 * Get id
	 * 
	 * @return id
	 **/
	@NotNull
	@Schema(example = "100000", required = true, description = "")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Show title(String title) {
		this.title = title;
		return this;
	}

	/**
	 * Get title
	 * 
	 * @return title
	 **/
	@Schema(example = "Spettacolo 1", description = "")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Show time(String time) {
		this.time = time;
		return this;
	}

	/**
	 * Time of the show in HH:mm format
	 * 
	 * @return time
	 **/
	@Schema(example = "20:30", description = "Time of the show in HH:mm format")
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Show from(LocalDate from) {
		this.from = from;
		return this;
	}

	/**
	 * Date of the show in yyyy-MM-dd format.
	 * 
	 * @return from
	 **/
	@Schema(example = "2024-10-10", description = "Date of the show in yyyy-MM-dd format.")
	public LocalDate getFrom() {
		return from;
	}

	public void setFrom(LocalDate day) {
		this.from = day;
	}

	public Show to(LocalDate to) {
		this.to = to;
		return this;
	}

	/**
	 * Date of the show in yyyy-MM-dd format.
	 * 
	 * @return to
	 **/
	@Schema(example = "2024-10-10", description = "Date of the show in yyyy-MM-dd format.")
	public LocalDate getTo() {
		return to;
	}

	public void setTo(LocalDate to) {
		this.to = to;
	}

	public Show duration(Long duration) {
		this.duration = duration;
		return this;
	}

	/**
	 * Duration of the show in minutes.
	 * 
	 * @return duration
	 **/
	@Schema(example = "90", description = "Duration of the show in minutes.")
	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public Show idRoom(Long idRoom) {
		this.idRoom = idRoom;
		return this;
	}

	/**
	 * Get idRoom
	 * 
	 * @return idRoom
	 **/
	@Schema(example = "100000", description = "")
	public Long getIdRoom() {
		return idRoom;
	}

	public void setIdRoom(Long idRoom) {
		this.idRoom = idRoom;
	}

	public Show room(Room room) {
		this.room = room;
		return this;
	}

	/**
	 * Get room
	 * 
	 * @return room
	 **/
	@Valid
	@Schema(description = "")
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public boolean equals(java.lang.Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Show show = (Show) o;
		return Objects.equals(this.id, show.id) && Objects.equals(this.title, show.title)
				&& Objects.equals(this.time, show.time) && Objects.equals(this.from, show.from)
				&& Objects.equals(this.to, show.to) && Objects.equals(this.duration, show.duration)
				&& Objects.equals(this.idRoom, show.idRoom) && Objects.equals(this.room, show.room);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, title, time, from, to, duration, idRoom, room);
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("class Show {\n");

		sb.append("    id: ").append(toIndentedString(id)).append("\n");
		sb.append("    title: ").append(toIndentedString(title)).append("\n");
		sb.append("    time: ").append(toIndentedString(time)).append("\n");
		sb.append("    from: ").append(toIndentedString(from)).append("\n");
		sb.append("    to: ").append(toIndentedString(to)).append("\n");
		sb.append("    duration: ").append(toIndentedString(duration)).append("\n");
		sb.append("    idRoom: ").append(toIndentedString(idRoom)).append("\n");
		sb.append("    room: ").append(toIndentedString(room)).append("\n");
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
