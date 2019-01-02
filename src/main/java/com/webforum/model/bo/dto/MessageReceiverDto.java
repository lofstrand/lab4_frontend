package com.webforum.model.bo.dto;

/**
 * @author Sebastian Löfstrand (selo@kth.se), Jonas Lundvall (jonlundv@kth.se)
 *
 *  Data transfer object for Message receivers
 */
public class MessageReceiverDto {
    // Properties ---------------------------------------------------------------------------------
    private Long id;
    private String userName;

    // Getters/setters ----------------------------------------------------------------------------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    // Constructors -------------------------------------------------------------------------------
    public MessageReceiverDto() {
    }

    public MessageReceiverDto(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }

    // Actions ------------------------------------------------------------------------------------
    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && id != null)
                ? id.equals(((MessageReceiverDto) other).id)
                : (other == this);
    }

    @Override
    public int hashCode() {
        return (id != null)
                ? (getClass().hashCode() + id.hashCode())
                : super.hashCode();
    }
}
