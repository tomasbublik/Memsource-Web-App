package cz.memsource.assignment.api;

import java.util.List;

public class Project {

    private String uid;

    private String id;

    private String name;

    private String sourceLang;

    private List<String> targetLangs;

    private String status;

    private String innerId;

    private String dateCreated;

    private String domain;

    private String subDomain;

    private String shared;

    private String client;

    private String costCenter;

    private String businessUnit;

    private String dateDue;

    private String purchaseOrder;

    private String jobBoard;

    private String note;

    private Owner owner;

    private Progress progress;

    public static class Owner {

        private String id;

        private String firstName;

        private String lastName;

        private String userName;

        private String email;

        private String role;

        private String timezone;

        private String active;

        private String deleted;

        private String terminologist;

        private String dateCreated;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getTimezone() {
            return timezone;
        }

        public void setTimezone(String timezone) {
            this.timezone = timezone;
        }

        public String getActive() {
            return active;
        }

        public void setActive(String active) {
            this.active = active;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }

        public String getTerminologist() {
            return terminologist;
        }

        public void setTerminologist(String terminologist) {
            this.terminologist = terminologist;
        }

        public String getDateCreated() {
            return dateCreated;
        }

        public void setDateCreated(String dateCreated) {
            this.dateCreated = dateCreated;
        }
    }

    public static class Progress {

        private String totalCount;

        private String finishedCount;

        private String overdueCount;

        public String getTotalCount() {
            return totalCount;
        }

        public void setTotalCount(String totalCount) {
            this.totalCount = totalCount;
        }

        public String getFinishedCount() {
            return finishedCount;
        }

        public void setFinishedCount(String finishedCount) {
            this.finishedCount = finishedCount;
        }

        public String getOverdueCount() {
            return overdueCount;
        }

        public void setOverdueCount(String overdueCount) {
            this.overdueCount = overdueCount;
        }
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSourceLang() {
        return sourceLang;
    }

    public void setSourceLang(String sourceLang) {
        this.sourceLang = sourceLang;
    }

    public List<String> getTargetLangs() {
        return targetLangs;
    }

    public String getTargetLangsString() {
        if (targetLangs != null && !targetLangs.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            for (String targetLang : targetLangs) {
                stringBuilder.append(getTargetLangs());
            }
            return stringBuilder.toString();
        }
        return "";
    }

    public void setTargetLangs(List<String> targetLangs) {
        this.targetLangs = targetLangs;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInnerId() {
        return innerId;
    }

    public void setInnerId(String innerId) {
        this.innerId = innerId;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSubDomain() {
        return subDomain;
    }

    public void setSubDomain(String subDomain) {
        this.subDomain = subDomain;
    }

    public String getShared() {
        return shared;
    }

    public void setShared(String shared) {
        this.shared = shared;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getDateDue() {
        return dateDue;
    }

    public void setDateDue(String dateDue) {
        this.dateDue = dateDue;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public String getJobBoard() {
        return jobBoard;
    }

    public void setJobBoard(String jobBoard) {
        this.jobBoard = jobBoard;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setProgress(Progress progress) {
        this.progress = progress;
    }
}
