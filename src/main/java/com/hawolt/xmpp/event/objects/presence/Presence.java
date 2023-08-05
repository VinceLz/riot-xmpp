package com.hawolt.xmpp.event.objects.presence;

import com.hawolt.xmpp.event.objects.presence.impl.*;
import org.json.JSONObject;

/**
 * Created: 13/04/2022 12:36
 * Author: Twitter @hawolt
 **/

public class Presence extends JSONObject {
    
    @Override
    public String toString() {
        String rawHtml = super.toString();
        int rawHtmlLength = rawHtml.length();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < rawHtmlLength; i++) {
            char ch = rawHtml.charAt(i);
            if (ch == '<') {
                builder.append("&lt;");
            } else if (ch == '>') {
                builder.append("&gt;");
            } else if (ch == '"') {
                builder.append("&quot;");
            } else if (ch == '&') {
                builder.append("&amp;");
            } else if (ch < ' ' || ch == '\'') {
                builder.append("&#").append((int) ch).append(';');
            } else {
                builder.append(ch);
            }
        }
        return builder.toString();
    }

    public static AbstractPresence create(JSONObject o) {
        if (!o.has("show")) {
            if (o.has("id")) {
                return new DisconnectPresence(o);
            } else {
                return new OfflinePresence(o);
            }
        } else {
            if (o.getString("show").equals("mobile")) {
                if (!o.has("last_online")) {
                    return new FakeMobilePresence(o);
                } else {
                    return new MobilePresence(o);
                }
            } else {
                if (!o.has("game")) {
                    if (!o.has("status")) {
                        return new DeceivePresence(o);
                    } else {
                        return new OnlinePresence(o);
                    }
                } else {
                    return new GamePresence(o);
                }
            }
        }
    }

    public static class Builder {
        private String championId, companionId, damageSkinId, gameQueueType, gameStatus, iconOverride, initRankStat, level, mapId, mapSkinId, masteryScore, profileIcon, puuid, skinVariant, skinname, timestamp, gameMode, observable, pty, bannerIdSelected, challengeCrystalLevel, challengePoints, challengeTitle, challengeTitleSelected, challengeTokensSelected, gameId, queueId, rankedLeagueDivision, rankedLeagueQueue, rankedLeagueTier;
        private Regalia regalia = new Regalia();

        public Builder setRegalia(String bannerType, String crestType, String selectedPrestigeCrest) {
            this.regalia = new Regalia(bannerType, crestType, selectedPrestigeCrest);
            return this;
        }

        public Builder setChampionId(String championId) {
            this.championId = championId;
            return this;
        }

        public Builder setCompanionId(String companionId) {
            this.companionId = companionId;
            return this;
        }

        public Builder setDamageSkinId(String damageSkinId) {
            this.damageSkinId = damageSkinId;
            return this;
        }

        public Builder setGameQueueType(String gameQueueType) {
            this.gameQueueType = gameQueueType;
            return this;
        }

        public Builder setGameStatus(String gameStatus) {
            this.gameStatus = gameStatus;
            return this;
        }

        public Builder setIconOverride(String iconOverride) {
            this.iconOverride = iconOverride;
            return this;
        }

        public Builder setInitRankStat(String initRankStat) {
            this.initRankStat = initRankStat;
            return this;
        }

        public Builder setLevel(String level) {
            this.level = level;
            return this;
        }

        public Builder setMapId(String mapId) {
            this.mapId = mapId;
            return this;
        }

        public Builder setMapSkinId(String mapSkinId) {
            this.mapSkinId = mapSkinId;
            return this;
        }

        public Builder setMasteryScore(String masteryScore) {
            this.masteryScore = masteryScore;
            return this;
        }

        public Builder setProfileIcon(String profileIcon) {
            this.profileIcon = profileIcon;
            return this;
        }

        public Builder setPuuid(String puuid) {
            this.puuid = puuid;
            return this;
        }

        public Builder setSkinVariant(String skinVariant) {
            this.skinVariant = skinVariant;
            return this;
        }

        public Builder setSkinname(String skinname) {
            this.skinname = skinname;
            return this;
        }

        public Builder setTimestamp(String timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setGameMode(String gameMode) {
            this.gameMode = gameMode;
            return this;
        }

        public Builder setIsObservable(String observable) {
            this.observable = observable;
            return this;
        }

        public Builder setPTY(String s) {
            this.pty = pty;
            return this;
        }

        public Builder setBannerIdSelected(String bannerIdSelected) {
            this.bannerIdSelected = bannerIdSelected;
            return this;
        }

        public Builder setChallengeCrystalLevel(String challengeCrystalLevel) {
            this.challengeCrystalLevel = challengeCrystalLevel;
            return this;
        }

        public Builder setChallengePoints(String challengePoints) {
            this.challengePoints = challengePoints;
            return this;
        }

        public Builder setChallengeTitle(String challengeTitle) {
            this.challengeTitle = challengeTitle;
            return this;
        }

        public Builder setChallengeTitleSelected(String challengeTitleSelected) {
            this.challengeTitleSelected = challengeTitleSelected;
            return this;
        }

        public Builder setChallengeTokensSelected(String challengeTokensSelected) {
            this.challengeTokensSelected = challengeTokensSelected;
            return this;
        }

        public Builder setObservable(String observable) {
            this.observable = observable;
            return this;
        }

        public Builder setGameId(String gameId) {
            this.gameId = gameId;
            return this;
        }

        public Builder setQueueId(String queueId) {
            this.queueId = queueId;
            return this;
        }

        public Builder setRankedLeagueDivision(String rankedLeagueDivision) {
            this.rankedLeagueDivision = rankedLeagueDivision;
            return this;
        }

        public Builder setRankedLeagueQueue(String rankedLeagueQueue) {
            this.rankedLeagueQueue = rankedLeagueQueue;
            return this;
        }

        public Builder setRankedLeagueTier(String rankedLeagueTier) {
            this.rankedLeagueTier = rankedLeagueTier;
            return this;
        }

        public Presence build() {
            Presence presence = new Presence();
            if (bannerIdSelected != null) presence.put("bannerIdSelected", bannerIdSelected);
            if (challengeCrystalLevel != null) presence.put("challengeCrystalLevel", challengeCrystalLevel);
            if (challengePoints != null) presence.put("challengePoints", challengePoints);
            if (challengeTitle != null) presence.put("challengeTitle", challengeTitle);
            if (challengeTitleSelected != null) presence.put("challengeTitleSelected", challengeTitleSelected);
            if (challengeTokensSelected != null) presence.put("challengeTokensSelected", challengeTokensSelected);
            if (championId != null) presence.put("championId", championId);
            if (companionId != null) presence.put("companionId", companionId);
            if (damageSkinId != null) presence.put("damageSkinId", damageSkinId);
            if (gameId != null) presence.put("gameId", gameId);
            if (gameMode != null) presence.put("gameMode", gameMode);
            if (gameQueueType != null) presence.put("gameQueueType", gameQueueType);
            if (gameStatus != null) presence.put("gameStatus", gameStatus);
            if (iconOverride != null) presence.put("iconOverride", iconOverride);
            if (observable != null) presence.put("isObservable", observable);
            if (initRankStat != null) presence.put("initRankStat", initRankStat);
            if (level != null) presence.put("level", level);
            if (mapId != null) presence.put("mapId", mapId);
            if (mapSkinId != null) presence.put("mapSkinId", mapSkinId);
            if (masteryScore != null) presence.put("masteryScore", masteryScore);
            if (profileIcon != null) presence.put("profileIcon", profileIcon);
            if (pty != null) presence.put("pty", pty);
            if (puuid != null) presence.put("puuid", puuid);
            if (queueId != null) presence.put("queueId", queueId);
            if (rankedLeagueDivision != null) presence.put("rankedLeagueDivision", rankedLeagueDivision);
            if (rankedLeagueQueue != null) presence.put("rankedLeagueQueue", rankedLeagueQueue);
            if (rankedLeagueTier != null) presence.put("rankedLeagueTier", rankedLeagueTier);
            if (regalia != null) presence.put("regalia", regalia.build());
            if (skinVariant != null) presence.put("skinVariant", skinVariant);
            if (skinname != null) presence.put("skinname", skinname);
            if (timestamp != null) presence.put("timeStamp", timestamp);
            return presence;
        }
    }
}
