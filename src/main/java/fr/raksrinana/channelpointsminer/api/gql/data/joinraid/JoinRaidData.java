package fr.raksrinana.channelpointsminer.api.gql.data.joinraid;

import com.fasterxml.jackson.annotation.JsonProperty;
import fr.raksrinana.channelpointsminer.api.gql.data.types.JoinRaidPayload;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.jetbrains.annotations.NotNull;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
public class JoinRaidData{
	@JsonProperty("joinRaid")
	@NotNull
	private JoinRaidPayload joinRaid;
}
