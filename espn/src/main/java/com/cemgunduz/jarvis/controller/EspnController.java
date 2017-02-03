package com.cemgunduz.jarvis.controller;

import com.cemgunduz.jarvis.configuration.MailConfiguration;
import com.cemgunduz.jarvis.configuration.ServerNames;
import com.cemgunduz.jarvis.email.EmailInput;
import com.cemgunduz.jarvis.email.EmailSendingResponse;
import com.cemgunduz.jarvis.nba.calculators.SeasonReportCompiler;
import com.cemgunduz.jarvis.nba.calculators.TeamReportCompiler;
import com.cemgunduz.jarvis.nba.calculators.player.PlayerReport;
import com.cemgunduz.jarvis.nba.calculators.report.Reporter;
import com.cemgunduz.jarvis.nba.calculators.team.ReportablePlayerTeam;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by cem on 03/02/17.
 */

@RestController
public class EspnController {

    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private ServerNames serverNames;

    @Autowired
    private MailConfiguration mailConfiguration;

    @RequestMapping("/players")
    public void dailyLeagueReport()
    {
        SeasonReportCompiler seasonReportCompiler = new SeasonReportCompiler();
        List<PlayerReport> playerReports = seasonReportCompiler.compile();

        Reporter reporter = new Reporter();
        String reportAsString = reporter.compileReport(playerReports);

        EmailInput emailInput = new EmailInput();
        emailInput.setContent(reportAsString);
        emailInput.setFrom(mailConfiguration.getFrom());
        emailInput.setToList(mailConfiguration.getTo());
        emailInput.setSubject("Free Agents");

        sendMail(emailInput);
    }

    @RequestMapping("/teams")
    public void dailTeamReport()
    {
        TeamReportCompiler teamReportCompiler = new TeamReportCompiler();
        List<ReportablePlayerTeam> teamReports = teamReportCompiler.compile();

        Reporter reporter = new Reporter();
        String reportAsString = reporter.compileTeamReport(teamReports);

        EmailInput emailInput = new EmailInput();
        emailInput.setContent(reportAsString);
        emailInput.setFrom(mailConfiguration.getFrom());
        emailInput.setToList(mailConfiguration.getTo());
        emailInput.setSubject("Team Reports");

        sendMail(emailInput);
    }

    // TODO : Need a common client to remove this repetition from the microservices
    private void sendMail(EmailInput emailInput)
    {
        InstanceInfo info = eurekaClient.getNextServerFromEureka(serverNames.getPUBLISHER(), false);
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<EmailInput> entity = new HttpEntity<>(emailInput, headers);
        ResponseEntity<EmailSendingResponse> response =
                restTemplate.postForEntity(info.getHomePageUrl()+"/email", entity, EmailSendingResponse.class);

        if(!response.getStatusCode().equals(HttpStatus.OK)
                || !response.getBody().isSent())
        {
            // TODO : Log and warn
            response.getBody().getReason();
        }
    }
}
