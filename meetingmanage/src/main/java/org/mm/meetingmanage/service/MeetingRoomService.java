package org.mm.meetingmanage.service;

import org.mm.meetingmanage.mapper.MeetingRoomMapper;
import org.mm.meetingmanage.model.MeetingRoom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingRoomService {
    @Autowired
    private MeetingRoomMapper meetingRoomMapper;

     // 获取所有的会议室
    public List<MeetingRoom> getAllMr() {
        return meetingRoomMapper.getAllMr();
    }

     //根据id获取对应的会议室
    public MeetingRoom getMrById(Integer roomid) {
        return meetingRoomMapper.getMrById(roomid);
    }


     // 更新会议室信息

    public Integer updateroom(MeetingRoom meetingRoom) {
        return meetingRoomMapper.updateroom(meetingRoom);
    }


     //添加会议室操作

    public Integer addMr(MeetingRoom meetingRoom) {
        return meetingRoomMapper.addMr(meetingRoom);
    }
}
