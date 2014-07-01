package com.vahe;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by Hrant on 6/21/2014.
 */
@Entity
@javax.persistence.Table(name = "transcripts", schema = "", catalog = "okpanda_teachers_accounts")
public class TranscriptsEntity {
    @Id
    private int id;

    @Basic
    @javax.persistence.Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int chatid;

    @Basic
    @javax.persistence.Column(name = "chatid")
    public int getChatid() {
        return chatid;
    }

    public void setChatid(int chatid) {
        this.chatid = chatid;
    }

    private String teacher;

    @Basic
    @javax.persistence.Column(name = "teacher")
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    private String student;

    @Basic
    @javax.persistence.Column(name = "student")
    public String getStudent() {
        return student;
    }

    public void setStudent(String student) {
        this.student = student;
    }

    private Timestamp transactionprocessingdate;

    @Basic
    @javax.persistence.Column(name = "transactionprocessingdate")
    public Timestamp getTransactionprocessingdate() {
        return transactionprocessingdate;
    }

    public void setTransactionprocessingdate(Timestamp transactionprocessingdate) {
        this.transactionprocessingdate = transactionprocessingdate;
    }

    private Timestamp fchatdate;

    @Basic
    @javax.persistence.Column(name = "fchatdate")
    public Timestamp getFchatdate() {
        return fchatdate;
    }

    public void setFchatdate(Timestamp fchatdate) {
        this.fchatdate = fchatdate;
    }

    private Timestamp lchatdate;

    @Basic
    @javax.persistence.Column(name = "lchatdate")
    public Timestamp getLchatdate() {
        return lchatdate;
    }

    public void setLchatdate(Timestamp lchatdate) {
        this.lchatdate = lchatdate;
    }

    private int linescount;

    @Basic
    @javax.persistence.Column(name = "linescount")
    public int getLinescount() {
        return linescount;
    }

    public void setLinescount(int linescount) {
        this.linescount = linescount;
    }

    private int tlines;

    @Basic
    @javax.persistence.Column(name = "tlines")
    public int getTlines() {
        return tlines;
    }

    public void setTlines(int tlines) {
        this.tlines = tlines;
    }

    private int slines;

    @Basic
    @javax.persistence.Column(name = "slines")
    public int getSlines() {
        return slines;
    }

    public void setSlines(int slines) {
        this.slines = slines;
    }

    private int corrections;

    @Basic
    @javax.persistence.Column(name = "corrections")
    public int getCorrections() {
        return corrections;
    }

    public void setCorrections(int corrections) {
        this.corrections = corrections;
    }

    private int tresponses;

    @Basic
    @javax.persistence.Column(name = "tresponses")
    public int getTresponses() {
        return tresponses;
    }

    public void setTresponses(int tresponses) {
        this.tresponses = tresponses;
    }

    private int tresponsetime;

    @Basic
    @javax.persistence.Column(name = "tresponsetime")
    public int getTresponsetime() {
        return tresponsetime;
    }

    public void setTresponsetime(int tresponsetime) {
        this.tresponsetime = tresponsetime;
    }

    private int sessionscount;

    @Basic
    @javax.persistence.Column(name = "sessionscount")
    public int getSessionscount() {
        return sessionscount;
    }

    public void setSessionscount(int sessionscount) {
        this.sessionscount = sessionscount;
    }

    private int sessiondays;

    @Basic
    @javax.persistence.Column(name = "sessiondays")
    public int getSessiondays() {
        return sessiondays;
    }

    public void setSessiondays(int sessiondays) {
        this.sessiondays = sessiondays;
    }

    private int totaldelay;

    @Basic
    @javax.persistence.Column(name = "totaldelay")
    public int getTotaldelay() {
        return totaldelay;
    }

    public void setTotaldelay(int totaldelay) {
        this.totaldelay = totaldelay;
    }

    private int delaycount;

    @Basic
    @javax.persistence.Column(name = "delaycount")
    public int getDelaycount() {
        return delaycount;
    }

    public void setDelaycount(int delaycount) {
        this.delaycount = delaycount;
    }

    private int shortdelaycount;

    @Basic
    @javax.persistence.Column(name = "shortdelaycount")
    public int getShortdelaycount() {
        return shortdelaycount;
    }

    public void setShortdelaycount(int shortdelaycount) {
        this.shortdelaycount = shortdelaycount;
    }

    private int mediumdelaycount;

    @Basic
    @javax.persistence.Column(name = "mediumdelaycount")
    public int getMediumdelaycount() {
        return mediumdelaycount;
    }

    public void setMediumdelaycount(int mediumdelaycount) {
        this.mediumdelaycount = mediumdelaycount;
    }

    private int longdelaycount;

    @Basic
    @javax.persistence.Column(name = "longdelaycount")
    public int getLongdelaycount() {
        return longdelaycount;
    }

    public void setLongdelaycount(int longdelaycount) {
        this.longdelaycount = longdelaycount;
    }

    private String queryrun;

    @Basic
    @javax.persistence.Column(name = "queryrun")
    public String getQueryrun() {
        return queryrun;
    }

    public void setQueryrun(String queryrun) {
        this.queryrun = queryrun;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TranscriptsEntity that = (TranscriptsEntity) o;

        if (chatid != that.chatid) return false;
        if (corrections != that.corrections) return false;
        if (delaycount != that.delaycount) return false;
        if (id != that.id) return false;
        if (linescount != that.linescount) return false;
        if (longdelaycount != that.longdelaycount) return false;
        if (mediumdelaycount != that.mediumdelaycount) return false;
        if (sessiondays != that.sessiondays) return false;
        if (sessionscount != that.sessionscount) return false;
        if (shortdelaycount != that.shortdelaycount) return false;
        if (slines != that.slines) return false;
        if (tlines != that.tlines) return false;
        if (totaldelay != that.totaldelay) return false;
        if (tresponses != that.tresponses) return false;
        if (tresponsetime != that.tresponsetime) return false;
        if (fchatdate != null ? !fchatdate.equals(that.fchatdate) : that.fchatdate != null) return false;
        if (lchatdate != null ? !lchatdate.equals(that.lchatdate) : that.lchatdate != null) return false;
        if (queryrun != null ? !queryrun.equals(that.queryrun) : that.queryrun != null) return false;
        if (student != null ? !student.equals(that.student) : that.student != null) return false;
        if (teacher != null ? !teacher.equals(that.teacher) : that.teacher != null) return false;
        if (transactionprocessingdate != null ? !transactionprocessingdate.equals(that.transactionprocessingdate) : that.transactionprocessingdate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + chatid;
        result = 31 * result + (teacher != null ? teacher.hashCode() : 0);
        result = 31 * result + (student != null ? student.hashCode() : 0);
        result = 31 * result + (transactionprocessingdate != null ? transactionprocessingdate.hashCode() : 0);
        result = 31 * result + (fchatdate != null ? fchatdate.hashCode() : 0);
        result = 31 * result + (lchatdate != null ? lchatdate.hashCode() : 0);
        result = 31 * result + linescount;
        result = 31 * result + tlines;
        result = 31 * result + slines;
        result = 31 * result + corrections;
        result = 31 * result + tresponses;
        result = 31 * result + tresponsetime;
        result = 31 * result + sessionscount;
        result = 31 * result + sessiondays;
        result = 31 * result + totaldelay;
        result = 31 * result + delaycount;
        result = 31 * result + shortdelaycount;
        result = 31 * result + mediumdelaycount;
        result = 31 * result + longdelaycount;
        result = 31 * result + (queryrun != null ? queryrun.hashCode() : 0);
        return result;
    }
}
