package fr.raksrinana.channelpointsminer.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.raksrinana.channelpointsminer.util.json.URLDeserializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.Nullable;
import java.net.URL;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DiscordConfiguration{
	@JsonProperty("webhookUrl")
	@JsonDeserialize(using = URLDeserializer.class)
	@Nullable
	@Comment("URL of the webhook")
	private URL url;
	@JsonProperty("embeds")
	@Builder.Default
	@Comment("Use embeds in the messages or not")
	private boolean embeds = false;
}
