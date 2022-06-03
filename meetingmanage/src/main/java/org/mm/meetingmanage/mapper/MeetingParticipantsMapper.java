package org.mm.meetingmanage.mapper;

import java.util.List;

public interface MeetingParticipantsMapper {
    List<Integer> getAllBymeetingid(Integer meetingid);
}
