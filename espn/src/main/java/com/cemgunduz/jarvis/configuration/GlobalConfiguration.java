package com.cemgunduz.jarvis.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cem on 25/10/16.
 */

// TODO : Also replicated needs to be removed
@RefreshScope
@Configuration
public class GlobalConfiguration {

    @Value("${cookie}")
    private String cookie;

    public String getCookie() {

        if(cookie == null || cookie.isEmpty())
            return "fbalm_playertableviewset_clubhouse=stats#lastSeason; espnfba_transuccessx=142666|3#2_722_-1_1001_3_12; fbalm_playertableviewset_freeagency=stats#projections; FBA_LM_COOKIE=AAAAARoAAAAFAQALY291bnRyeUNvZGUBAANubGQBAA9jbGllbnRJUEFkZHJlc3MBAA8yMTcuMTQ5LjEzNS4xMjQBAApmcm9tVGVhbUlkAwAAAAMBAAhzZWFzb25JZAMAAAfhAQAIbGVhZ3VlSWQDAAItSg; UNID=d0fbd88d-80c8-4666-acaf-02f9742dbe84; __gads=ID=c44841bf04914d70:T=1476197104:S=ALNI_MYearYQ5G6aj2J2sB0ols_4SJhiXQ; optimizelyEndUserId=oeu1477142774672r0.16657488902536732; optimizelySegments=%7B%22310954789%22%3A%22none%22%2C%22311043393%22%3A%22false%22%2C%22311047346%22%3A%22gc%22%2C%22311052307%22%3A%22search%22%2C%22793783561%22%3A%22true%22%2C%22806111078%22%3A%22nbadraft%22%2C%22806950111%22%3A%22desktop%22%7D; optimizelyBuckets=%7B%7D; grvinsights=9acef957b583766e54f1b0bc6c43c756; ESPN-INSIDER-PROD-ac=XTR; _cb_ls=1; mp_6893c4491047e14ee49156ed3f91c9a5_mixpanel=%7B%22distinct_id%22%3A%20%2288C95830-C1E8-40D4-8958-30C1E850D4A1%22%2C%22%24search_engine%22%3A%20%22google%22%2C%22%24initial_referrer%22%3A%20%22https%3A%2F%2Fwww.google.nl%2F%22%2C%22%24initial_referring_domain%22%3A%20%22www.google.nl%22%7D; ESPN-FANTASYLM-PROD-ac=XTR; ESPN-FANTASYLM-PROD.auth=disneyid; _cb=ChdwpYCc_7VTTrBCP; _chartbeat2=.1477143118884.1477146394063.1; CRBLM=CBLM-001:AAAAAAABB3cAAAABB4sAAAABArAAAAABAEoAAAABAzgAAAABA3gAAAABBNsAAAABBFMAAAABCN0AAAABBFEAAAABB/YAAAABB/UAAAAB; CRBLM_LAST_UPDATE=1477298065:{88C95830-C1E8-40D4-8958-30C1E850D4A1}; ctoLocalVisitor={%22user_visitor_id_local%22:%221476197105859-6669027713900%22}; ctoVisitor={%22sessionCount%22:8}; mp_6b347c39f0f57067a36acc6aa600a094_mixpanel=%7B%22distinct_id%22%3A%20%22%7B88C95830-C1E8-40D4-8958-30C1E850D4A1%7D%22%2C%22%24initial_referrer%22%3A%20%22%24direct%22%2C%22%24initial_referring_domain%22%3A%20%22%24direct%22%2C%22__timers%22%3A%20%7B%22Social%20Share%22%3A%201477298065597%2C%22Searched%20Keyword%22%3A%201477298065597%2C%22Video%20Started%22%3A%201477298065597%2C%22Video%20Ended%22%3A%201477298065597%2C%22Now%20Card%20Engagement%22%3A%201477298065597%2C%22Favorited%22%3A%201477298065597%7D%2C%22%24search_engine%22%3A%20%22google%22%2C%22SWID%22%3A%20%22%7B88C95830-C1E8-40D4-8958-30C1E850D4A1%7D%22%2C%22Has%20Favorites%22%3A%20%22FALSE%22%2C%22Insider%22%3A%20%22FALSE%22%2C%22Fantasy%20Player%22%3A%20%22FALSE%22%2C%22User%20Time%20Zone%22%3A%20%22CEST%22%2C%22User%20Auto-Play%20Setting%22%3A%20%22On%22%7D; _omnicwtest=works; DE2=bmxkO25oO2Ftc3RlcmRhbTticm9hZGJhbmQ7NTs1OzU7LTE7NTIuMzc4Mjc7NC45MDU5OTs1Mjg7Mzk2NjsxNzE4OzU7bmw7; DS=c2lnbmV0Lm5sOzA7c2lnbmV0IGIudi47; AMCVS_EE0201AC512D2BE80A490D4C%40AdobeOrg=1; AMCV_EE0201AC512D2BE80A490D4C%40AdobeOrg=-1248264605%7CMCAID%7C2BE6BDA78530ED3E-60000304E00116E4%7CMCMID%7C85597902080248317310829871109116105965%7CMCAAMLH-1477749770%7C6%7CMCAAMB-1478004473%7CNRX38WO0n5BH8Th-nqAG_A%7CMCOPTOUT-1477406873s%7CNONE%7CMCIDTS%7C17099; oidNAVY=XrZf5W7GhKsAkdTEUTljxWWoA2BRLjs#FkED3HnL3yiJoVml5vHW2OaVWn7mw8I5uYVLZQ7KphPHtAzba8p5nMQMl6Slmcd8z3IEYZrmhHs#tCeaFDzhuq1hkOO9IBomRRlxQkjoottt$tmae9$vgq8vLFUzpv$EJ6RuGMkomjzTodplvjviMqFdPlA2sBlbzjaFE30khrstTTe5TLBmv3OINwVUsYnXzaCu2N3sXiabM60TafthKXAnqiF7I8utvv8vqasT$neihlKTVdck5bAgbsase2T6TvpcLxKy$rc; espn_s2=AECLcnaloOW6VhIeSPtkr4wC8A1ECtvxBSrtrL%2F%2FipvjIZ6VObp7BKyMBevkv7DdD1SPN7T71XIcHjwN4SSSNeDEi6vqp3%2F8%2Fs4Ps7jsoXgoEdPjGFbQ0qcM1Ghc%2FI1J%2B%2B3kqMs3R4sjRDXyoprldB6x7CBhLCqRhmDmQQLgLbuWvvoHHX8zqKWF74TsiSlexzYrQpkNPCPB19VdOnfCl1xxf9zjNX9M02ht2yVdc3ek9KmpzKNhygs54gO5gQJv7QEXQdBf6D01uYDcqtZieVUx; _omnicwtest=1477403384337; s_vi=[CS]v1|2BE6BDA78530ED3E-60000304E00116E4[CE]; SWID={88C95830-C1E8-40D4-8958-30C1E850D4A1}; espnAuth={\"swid\":\"{88C95830-C1E8-40D4-8958-30C1E850D4A1}\"}; s_pers=%20s_c24%3D1477405013406%7C1572013013406%3B%20s_c24_s%3DLess%2520than%25201%2520day%7C1477406813406%3B%20s_gpv_pn%3Dfantasy%253Abasketball%253Afba%253Afreeagency%7C1477406813414%3B; s_sess=%20s_ppv%3D91%3B%20s_cc%3Dtrue%3B%20s_omni_lid%3Datxt%252B%255Efantasy%253Abasketball%253Afba%253Afreeagency%3B%20s_sq%3Dwdgespfantasy%252Cwdgespge%253D%252526pid%25253Dfantasy%2525253Abasketball%2525253Afba%2525253Afreeagency%252526pidt%25253D1%252526oid%25253Dhttp%2525253A%2525252F%2525252Fgames.espn.com%2525252Ffba%2525252Ffreeagency%2525253FleagueId%2525253D142666%25252526teamId%2525253D3%25252526seasonId%2525253D2017%25252526avail%2525253D-1%252526ot%25253DA%3B";

        return cookie;
    }
}
