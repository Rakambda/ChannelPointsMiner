package fr.raksrinana.twitchminer.api.gql.data.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.raksrinana.twitchminer.utils.json.UnknownDeserializer;
import lombok.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@JsonTypeName("Channel")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class Channel extends GQLType{
	@JsonProperty("id")
	@NotNull
	private String id;
	@JsonProperty("self")
	@Nullable
	private ChannelSelfEdge self;
	@JsonProperty("communityPointsSettings")
	@Nullable
	private CommunityPointsChannelSettings communityPointsSettings;
	@JsonProperty("viewerDropCampaigns")
	@JsonDeserialize(using = UnknownDeserializer.class)
	private Object viewerDropCampaigns;
}
