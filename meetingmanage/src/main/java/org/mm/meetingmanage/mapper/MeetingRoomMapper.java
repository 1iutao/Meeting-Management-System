package org.mm.meetingmanage.mapper;

import org.mm.meetingmanage.model.MeetingRoom;
import org.mm.meetingmanage.model.RoomDTO;

import java.util.List;

public interface MeetingRoomMapper {
    List<RoomDTO> getAll();

    List<MeetingRoom> getAllMr();

    MeetingRoom getMrById(Integer roomid);

    Integer updateroom(MeetingRoom meetingRoom);

    Integer addMr(MeetingRoom meetingRoom);
}
