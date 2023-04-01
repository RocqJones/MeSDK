package com.rocqjones.me_logic.models

import androidx.annotation.DrawableRes
import com.rocqjones.me_logic.providers.LocalAccountsDataProvider

/**
 * A simple data class to represent an Email.
 */
data class Email(
    val id: Long,
    val sender: Account,
    val recipients: List<Account> = emptyList(),
    val subject: String = "",
    val body: String = "",
    val attachments: List<EmailAttachment> = emptyList(),
    var isImportant: Boolean = false,
    var isStarred: Boolean = false,
    var mailbox: MailboxType = MailboxType.INBOX,
    var createAt: String,
    val threads: List<Email> = emptyList()
) {
    val senderPreview: String = "${sender.fullName} - 4 hrs ago"
    val hasBody: Boolean = body.isNotBlank()
    val hasAttachments: Boolean = attachments.isNotEmpty()
    val recipientsPreview: String = recipients
        .map { it.firstName }
        .fold("") { name, acc -> "$acc, $name" }
    val nonUserAccountRecipients = recipients
        .filterNot { LocalAccountsDataProvider.isUserAccount(it.uid) }
}

/**
 * An object which represents an account which can belong to a user. A single user can have
 * multiple accounts.
 */
data class Account(
    val id: Long,
    val uid: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val altEmail: String,
    @DrawableRes val avatar: Int,
    var isCurrentAccount: Boolean = false
) {
    val fullName: String = "$firstName $lastName"

}

/**
 * An object class to define an attachment to email object.
 */
data class EmailAttachment(
    @DrawableRes val resId: Int,
    val contentDesc: String
)

/**
 * An enum class to define different types of email folders or categories.
 */
enum class MailboxType {
    INBOX, DRAFTS, SENT, SPAM, TRASH
}