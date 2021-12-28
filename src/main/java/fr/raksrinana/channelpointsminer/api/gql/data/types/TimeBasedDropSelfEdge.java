package fr.raksrinana.channelpointsminer.api.gql.data.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

@JsonTypeName("TimeBasedDropSelfEdge")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class TimeBasedDropSelfEdge extends GQLType{
	@JsonProperty("hasPreconditionsMet")
	private boolean hasPreconditionsMet;
	@JsonProperty("currentMinutesWatched")
	private int currentMinutesWatched;
	@JsonProperty("isClaimed")
	private boolean isClaimed;
	@JsonProperty("dropInstanceID")
	@Nullable
	private String dropInstanceId;
}
