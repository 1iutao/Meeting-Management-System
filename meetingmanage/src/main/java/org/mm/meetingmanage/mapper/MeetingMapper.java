package org.mm.meetingmanage.mapper;

import org.apache.ibatis.annotations.Param;
import org.mm.meetingmanage.model.Meeting;
import org.mm.meetingmanage.model.MeetingDTO;

import java.util.List;

public interface MeetingMapper {
    List<Meeting> getMeetingById(Integer employeeid);

    Integer addMeeting(Meeting meeting);

    void addParticipants(@Param("meetingid") Integer meetingid, @Param("mps") Integer[] mps);

    List<MeetingDTO> listMeetingDTOs(@Param("mdto") MeetingDTO meetingDTO, @Param("page") Integer page, @Param("pagesize") Integer pagesize);

    Long getTotal(@Param("mdto") MeetingDTO meetingDTO);

    Meeting getMeetingByid(Integer meetingid);

    List<Meeting> getCancelMeeting();

    List<MeetingDTO> getmeetingofmybook(Integer employeeid);

    void cancelmeeting(@Param("meetingid") Integer meetingid, @Param("canceledreason") String canceledreason);
}
