package com.simon.documentgeneration.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentJobRegulationResponse {


    String districtName;

    Integer sectionNumber;

    String judgeName;

    Boolean actingJudge;

    String judgeOrganizerName;

    Boolean actingJudgeOrganizer;

    String concordantName;
}
