/**
CreepTD is an online multiplayer towerdefense game
formerly created under the name CreepSmash as a project
at the Hochschule fuer Technik Stuttgart (University of Applied Science)

CreepTD (Since version 0.7.0+) Copyright (C) 2011 by
 * Daniel Wirtz, virtunity media
http://www.creeptd.com

CreepSmash (Till version 0.6.0) Copyright (C) 2008 by
 * Andreas Wittig
 * Bernd Hietler
 * Christoph Fritz
 * Fabian Kessel
 * Levin Fritz
 * Nikolaj Langner
 * Philipp Schulte-Hubbert
 * Robert Rapczynski
 * Ron Trautsch
 * Sven Supper
http://creepsmash.sf.net/

This program is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.
 **/
package com.creeptd.common.messages.server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Entry of highscore-table.
 * 
 * @author andreas
 *
 */
public class HighscoreEntry {

    private static final String REG_EXP =
            "[\\s]?\"([^\"]+)\"\\s([0-9]+)\\s([0-9]+)\\s([-]?[0-9]+)\\s([-]?[0-9]+)[\\s]?";
    public static final Pattern PATTERN = Pattern.compile(REG_EXP);
    private String playerName;
    private Integer experience;
    private Integer elopoints;
    private Integer lastgame_experience;
    private Integer lastgame_elopoints;

    /**
     * @return the playerName
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * @param playerName the playerName to set
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getElopoints() {
        return elopoints;
    }

    public void setElopoints(Integer elopoints) {
        this.elopoints = elopoints;
    }

    public Integer getExperience() {
        return experience;
    }

    public void setExperience(Integer experience) {
        this.experience = experience;
    }

    public Integer getLastgameElopoints() {
        return lastgame_elopoints;
    }

    public void setLastgameElopoints(Integer lastgame_elopoints) {
        this.lastgame_elopoints = lastgame_elopoints;
    }

    public Integer getLastgameExperience() {
        return lastgame_experience;
    }

    public void setLastgameExperience(Integer lastgame_experience) {
        this.lastgame_experience = lastgame_experience;
    }

    /**
     * @return the String representation of the message.
     */
    @Override
    public String toString() {
        return "\"" + this.getPlayerName() + "\" " + this.getExperience() + " " + this.getElopoints() + " " + this.getLastgameExperience() + " " + this.getLastgameElopoints();
    }

    /**
     * @param messageString the messageString
     */
    public void initWithMessage(String messageString) {
        Matcher matcher = PATTERN.matcher(messageString);
        if (matcher.matches()) {
            this.setPlayerName(matcher.group(1));
            this.setExperience(Integer.parseInt(matcher.group(2)));
            this.setElopoints(Integer.parseInt(matcher.group(3)));
            this.setLastgameExperience(Integer.parseInt(matcher.group(4)));
            this.setLastgameElopoints(Integer.parseInt(matcher.group(5)));
        }
    }
}
