package fr.raksrinana.twitchminer.api.gql.data.types;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.raksrinana.twitchminer.utils.json.ISO8601ZonedDateTimeDeserializer;
import lombok.*;
import java.time.ZonedDateTime;

@JsonTypeName("CommunityPointsLastViewedContentByTypeAndID")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
@ToString
public class CommunityPointsLastViewedContentByTypeAndID extends GQLType{
	@JsonProperty("contentID")
	private ContentId contentId;
	@JsonProperty("contentType")
	private ContentType contentType;
	@JsonProperty("lastViewedAt")
	@JsonDeserialize(using = ISO8601ZonedDateTimeDeserializer.class)
	private ZonedDateTime lastViewedAt;
}
