package fr.raksrinana.channelpointsminer.factory;

import fr.raksrinana.channelpointsminer.cli.CLIHolder;
import fr.raksrinana.channelpointsminer.cli.CLIParameters;
import fr.raksrinana.channelpointsminer.config.AccountConfiguration;
import fr.raksrinana.channelpointsminer.config.AnalyticsConfiguration;
import fr.raksrinana.channelpointsminer.config.Configuration;
import fr.raksrinana.channelpointsminer.config.DatabaseConfiguration;
import fr.raksrinana.channelpointsminer.config.DiscordConfiguration;
import fr.raksrinana.channelpointsminer.config.StreamerDirectory;
import fr.raksrinana.channelpointsminer.streamer.StreamerSettings;
import fr.raksrinana.channelpointsminer.tests.TestUtils;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ConfigurationFactoryTest{
	@Mock
	private CLIParameters cliParameters;
	
	@BeforeEach
	void setUp(){
		ConfigurationFactory.resetInstance();
	}
	
	@Test
	void getInstanceDefaults() throws MalformedURLException{
		var testConfig = TestUtils.getResourcePath("config/config-minimalistic.json");
		when(cliParameters.getConfigurationFile()).thenReturn(testConfig);
		
		var expected = Configuration.builder()
				.accounts(List.of(AccountConfiguration.builder()
						.username("username")
						.password("password")
						.use2Fa(false)
						.loadFollows(false)
						.enabled(true)
						.defaultStreamerSettings(StreamerSettings.builder()
								.makePredictions(false)
								.followRaid(false)
								.participateCampaigns(false)
								.build())
						.streamerConfigDirectories(List.of(StreamerDirectory.builder()
								.path(Paths.get("streamers"))
								.recursive(false)
								.build()))
						.discord(DiscordConfiguration.builder()
								.url(new URL("https://discord-webhook"))
								.embeds(false)
								.build())
						.reloadEvery(15)
						.analytics(AnalyticsConfiguration.builder()
								.enabled(false)
								.build())
						.build()))
				.build();
		
		try(var cliHolder = Mockito.mockStatic(CLIHolder.class)){
			cliHolder.when(CLIHolder::getInstance).thenReturn(cliParameters);
			
			var firstInstance = ConfigurationFactory.getInstance();
			var secondInstance = ConfigurationFactory.getInstance();
			
			assertThat(firstInstance).usingRecursiveComparison().isEqualTo(expected);
			assertThat(secondInstance).isSameAs(firstInstance);
		}
	}
	
	@Test
	void getInstanceOverridden() throws MalformedURLException{
		var testConfig = TestUtils.getResourcePath("config/config-with-more-customization.json");
		when(cliParameters.getConfigurationFile()).thenReturn(testConfig);
		
		var expected = Configuration.builder()
				.accounts(List.of(AccountConfiguration.builder()
						.username("username")
						.password("password")
						.use2Fa(true)
						.loadFollows(true)
						.enabled(false)
						.defaultStreamerSettings(StreamerSettings.builder()
								.makePredictions(true)
								.followRaid(true)
								.participateCampaigns(true)
								.build())
						.streamerConfigDirectories(List.of(StreamerDirectory.builder()
								.path(Paths.get("streamers"))
								.recursive(true)
								.build()))
						.discord(DiscordConfiguration.builder()
								.url(new URL("https://discord-webhook"))
								.embeds(true)
								.build())
						.reloadEvery(15)
						.analytics(AnalyticsConfiguration.builder()
								.enabled(true)
								.database(DatabaseConfiguration.builder()
										.host("host")
										.port(1234)
										.username("user")
										.password("pass")
										.database("db")
										.build())
								.build())
						.build()))
				.build();
		
		try(var cliHolder = Mockito.mockStatic(CLIHolder.class)){
			cliHolder.when(CLIHolder::getInstance).thenReturn(cliParameters);
			
			var firstInstance = ConfigurationFactory.getInstance();
			var secondInstance = ConfigurationFactory.getInstance();
			
			assertThat(firstInstance).usingRecursiveComparison().isEqualTo(expected);
			assertThat(secondInstance).isSameAs(firstInstance);
		}
	}
	
	@Test
	void noFile(){
		var testConfig = Paths.get("fake/file.json");
		when(cliParameters.getConfigurationFile()).thenReturn(testConfig);
		
		try(var cliHolder = Mockito.mockStatic(CLIHolder.class)){
			cliHolder.when(CLIHolder::getInstance).thenReturn(cliParameters);
			
			assertThrows(IllegalStateException.class, ConfigurationFactory::getInstance);
		}
	}
}