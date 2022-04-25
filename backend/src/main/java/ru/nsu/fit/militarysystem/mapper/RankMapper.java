package ru.nsu.fit.militarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import ru.nsu.fit.militarysystem.dto.RankDto;
import ru.nsu.fit.militarysystem.store.entity.Rank;

import java.util.List;

@Mapper
public interface RankMapper {
    RankMapper INSTANCE = Mappers.getMapper(RankMapper.class);

    RankDto rankToRankDto(Rank rank);

    Rank rankDtoToRank(RankDto rankDto);

    List<RankDto> ranksToRankDtos(List<Rank> ranks);

    List<Rank> rankDtosToRanks(List<RankDto> rankDtos);

    default Page<RankDto> ranksToRankDtos(Page<Rank> ranks) {
        return ranks.map(INSTANCE::rankToRankDto);
    }

    default Page<Rank> rankDtosToRanks(Page<RankDto> rankDtos) {
        return rankDtos.map(INSTANCE::rankDtoToRank);
    }
}
