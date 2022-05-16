package fr.raksrinana.channelpointsminer.viewer.api;

import fr.raksrinana.channelpointsminer.viewer.api.data.ChannelData;
import fr.raksrinana.channelpointsminer.viewer.repository.entity.ChannelEntity;
import fr.raksrinana.channelpointsminer.viewer.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Collection;

@Service
public class ChannelService {
    private final ChannelRepository channelRepository;
    
    @Autowired
    public ChannelService(ChannelRepository channelRepository){
        this.channelRepository = channelRepository;
    }

    public Collection<ChannelData> listAll(){
        return channelRepository.findAll().stream()
                .map(this::entityToData)
                .toList();
    }
    
    private ChannelData entityToData(ChannelEntity entity){
        return ChannelData.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .build();
    }
}
