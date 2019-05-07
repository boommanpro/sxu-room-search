package cn.boommanpro.sxu.crawler.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class JxlRoomDataList {
    private String time;
    private String jxlValue;
    private List<RoomDataDto> roomDataDtoList;
}
