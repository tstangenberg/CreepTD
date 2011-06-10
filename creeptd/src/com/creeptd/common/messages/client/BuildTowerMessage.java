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
package com.creeptd.common.messages.client;

import java.awt.Point;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.creeptd.common.messages.MessageUtil;

/**
 * Message from client for building a tower.
 * 
 * @author andreas
 *
 */
public class BuildTowerMessage extends ClientMessage implements GameMessage {

    /**
     * regular expression for message-parsing.
     */
    private static final String REGEXP_BUILD_TOWER =
            "BUILD_TOWER\\s\"([^\"]+)\"\\sAT\\s([0-9]+),([0-9]+)\\s([0-9]+)";
    /**
     * pattern for regular expression.
     */
    public static final Pattern PATTERN = Pattern.compile(REGEXP_BUILD_TOWER);
    private String towerType;
    private Point position;
    private long roundId;

    /**
     * @return the tower type
     */
    public String getTowerType() {
        return this.towerType;
    }

    /**
     * @param towerType the tower type
     */
    public void setTowerType(String towerType) {
        this.towerType = towerType;
    }

    /**
     * @return the postion
     */
    public Point getPosition() {
        return this.position;
    }

    /**
     * @param position the postion
     */
    public void setPosition(Point position) {
        this.position = position;
    }

    /**
     * @param messageString the message as String.
     */
    @Override
    public void initWithMessage(String messageString) {
        Matcher matcher = PATTERN.matcher(messageString);
        if (matcher.matches()) {
            this.setTowerType(matcher.group(1));
            this.position = new Point();
            this.position.x = Integer.valueOf(matcher.group(2));
            this.position.y = Integer.valueOf(matcher.group(3));
            this.setPosition(this.position);
            this.setRoundId(Long.valueOf(matcher.group(4)));
        }
    }

    /**
     * @return the message as String.
     */
    @Override
    public String getMessageString() {
        return "BUILD_TOWER \"" + MessageUtil.prepareToSend(this.getTowerType()) + "\" AT " + this.getPosition().x + "," + this.getPosition().y + " " + this.getRoundId();
    }

    /**
     * @param incomeRoundId the incomeRoundId to set
     */
    public void setRoundId(long roundId) {
        this.roundId = roundId;
    }

    /**
     * @return the incomeRoundId
     */
    public long getRoundId() {
        return roundId;
    }
}
