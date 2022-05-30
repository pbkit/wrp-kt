@file:OptIn(pbandk.PublicForGeneratedCode::class)

package dev.pbkit.wrp

@pbandk.Export
public data class WrpMessage(
    val message: Message<*>? = null,
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    public sealed class Message<V>(value: V) : pbandk.Message.OneOf<V>(value) {
        public class HostInitialize(hostInitialize: dev.pbkit.wrp.WrpHostMessageInitialize) : Message<dev.pbkit.wrp.WrpHostMessageInitialize>(hostInitialize)
        public class HostError(hostError: dev.pbkit.wrp.WrpHostMessageError) : Message<dev.pbkit.wrp.WrpHostMessageError>(hostError)
        public class HostResStart(hostResStart: dev.pbkit.wrp.WrpHostMessageResStart) : Message<dev.pbkit.wrp.WrpHostMessageResStart>(hostResStart)
        public class HostResPayload(hostResPayload: dev.pbkit.wrp.WrpHostMessageResPayload) : Message<dev.pbkit.wrp.WrpHostMessageResPayload>(hostResPayload)
        public class HostResFinish(hostResFinish: dev.pbkit.wrp.WrpHostMessageResFinish) : Message<dev.pbkit.wrp.WrpHostMessageResFinish>(hostResFinish)
        public class GuestReqStart(guestReqStart: dev.pbkit.wrp.WrpGuestMessageReqStart) : Message<dev.pbkit.wrp.WrpGuestMessageReqStart>(guestReqStart)
        public class GuestReqPayload(guestReqPayload: dev.pbkit.wrp.WrpGuestMessageReqPayload) : Message<dev.pbkit.wrp.WrpGuestMessageReqPayload>(guestReqPayload)
        public class GuestReqFinish(guestReqFinish: dev.pbkit.wrp.WrpGuestMessageReqFinish) : Message<dev.pbkit.wrp.WrpGuestMessageReqFinish>(guestReqFinish)
        public class GuestResFinish(guestResFinish: dev.pbkit.wrp.WrpGuestMessageResFinish) : Message<dev.pbkit.wrp.WrpGuestMessageResFinish>(guestResFinish)
    }

    val hostInitialize: dev.pbkit.wrp.WrpHostMessageInitialize?
        get() = (message as? Message.HostInitialize)?.value
    val hostError: dev.pbkit.wrp.WrpHostMessageError?
        get() = (message as? Message.HostError)?.value
    val hostResStart: dev.pbkit.wrp.WrpHostMessageResStart?
        get() = (message as? Message.HostResStart)?.value
    val hostResPayload: dev.pbkit.wrp.WrpHostMessageResPayload?
        get() = (message as? Message.HostResPayload)?.value
    val hostResFinish: dev.pbkit.wrp.WrpHostMessageResFinish?
        get() = (message as? Message.HostResFinish)?.value
    val guestReqStart: dev.pbkit.wrp.WrpGuestMessageReqStart?
        get() = (message as? Message.GuestReqStart)?.value
    val guestReqPayload: dev.pbkit.wrp.WrpGuestMessageReqPayload?
        get() = (message as? Message.GuestReqPayload)?.value
    val guestReqFinish: dev.pbkit.wrp.WrpGuestMessageReqFinish?
        get() = (message as? Message.GuestReqFinish)?.value
    val guestResFinish: dev.pbkit.wrp.WrpGuestMessageResFinish?
        get() = (message as? Message.GuestResFinish)?.value

    override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.WrpMessage = protoMergeImpl(other)
    override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpMessage> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }
    public companion object : pbandk.Message.Companion<dev.pbkit.wrp.WrpMessage> {
        public val defaultInstance: dev.pbkit.wrp.WrpMessage by lazy { dev.pbkit.wrp.WrpMessage() }
        override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.WrpMessage = dev.pbkit.wrp.WrpMessage.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpMessage> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.WrpMessage, *>>(9)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Host_Initialize",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = dev.pbkit.wrp.WrpHostMessageInitialize.Companion),
                        oneofMember = true,
                        jsonName = "HostInitialize",
                        value = dev.pbkit.wrp.WrpMessage::hostInitialize
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Host_Error",
                        number = 2,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = dev.pbkit.wrp.WrpHostMessageError.Companion),
                        oneofMember = true,
                        jsonName = "HostError",
                        value = dev.pbkit.wrp.WrpMessage::hostError
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Host_ResStart",
                        number = 3,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = dev.pbkit.wrp.WrpHostMessageResStart.Companion),
                        oneofMember = true,
                        jsonName = "HostResStart",
                        value = dev.pbkit.wrp.WrpMessage::hostResStart
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Host_ResPayload",
                        number = 4,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = dev.pbkit.wrp.WrpHostMessageResPayload.Companion),
                        oneofMember = true,
                        jsonName = "HostResPayload",
                        value = dev.pbkit.wrp.WrpMessage::hostResPayload
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Host_ResFinish",
                        number = 5,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = dev.pbkit.wrp.WrpHostMessageResFinish.Companion),
                        oneofMember = true,
                        jsonName = "HostResFinish",
                        value = dev.pbkit.wrp.WrpMessage::hostResFinish
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Guest_ReqStart",
                        number = 6,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = dev.pbkit.wrp.WrpGuestMessageReqStart.Companion),
                        oneofMember = true,
                        jsonName = "GuestReqStart",
                        value = dev.pbkit.wrp.WrpMessage::guestReqStart
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Guest_ReqPayload",
                        number = 7,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = dev.pbkit.wrp.WrpGuestMessageReqPayload.Companion),
                        oneofMember = true,
                        jsonName = "GuestReqPayload",
                        value = dev.pbkit.wrp.WrpMessage::guestReqPayload
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Guest_ReqFinish",
                        number = 8,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = dev.pbkit.wrp.WrpGuestMessageReqFinish.Companion),
                        oneofMember = true,
                        jsonName = "GuestReqFinish",
                        value = dev.pbkit.wrp.WrpMessage::guestReqFinish
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Guest_ResFinish",
                        number = 9,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = dev.pbkit.wrp.WrpGuestMessageResFinish.Companion),
                        oneofMember = true,
                        jsonName = "GuestResFinish",
                        value = dev.pbkit.wrp.WrpMessage::guestResFinish
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpMessage",
                messageClass = dev.pbkit.wrp.WrpMessage::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
public data class WrpHostMessageInitialize(
    val availableMethods: List<String> = emptyList(),
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.WrpHostMessageInitialize = protoMergeImpl(other)
    override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpHostMessageInitialize> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }
    public companion object : pbandk.Message.Companion<dev.pbkit.wrp.WrpHostMessageInitialize> {
        public val defaultInstance: dev.pbkit.wrp.WrpHostMessageInitialize by lazy { dev.pbkit.wrp.WrpHostMessageInitialize() }
        override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.WrpHostMessageInitialize = dev.pbkit.wrp.WrpHostMessageInitialize.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpHostMessageInitialize> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.WrpHostMessageInitialize, *>>(1)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "available_methods",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Repeated<String>(valueType = pbandk.FieldDescriptor.Type.Primitive.String()),
                        jsonName = "availableMethods",
                        value = dev.pbkit.wrp.WrpHostMessageInitialize::availableMethods
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpHostMessage_Initialize",
                messageClass = dev.pbkit.wrp.WrpHostMessageInitialize::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
public data class WrpHostMessageError(
    val message: String = "",
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.WrpHostMessageError = protoMergeImpl(other)
    override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpHostMessageError> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }
    public companion object : pbandk.Message.Companion<dev.pbkit.wrp.WrpHostMessageError> {
        public val defaultInstance: dev.pbkit.wrp.WrpHostMessageError by lazy { dev.pbkit.wrp.WrpHostMessageError() }
        override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.WrpHostMessageError = dev.pbkit.wrp.WrpHostMessageError.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpHostMessageError> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.WrpHostMessageError, *>>(1)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "message",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "message",
                        value = dev.pbkit.wrp.WrpHostMessageError::message
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpHostMessage_Error",
                messageClass = dev.pbkit.wrp.WrpHostMessageError::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
public data class WrpHostMessageResStart(
    val reqId: String = "",
    val header: Map<String, String> = emptyMap(),
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.WrpHostMessageResStart = protoMergeImpl(other)
    override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpHostMessageResStart> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }
    public companion object : pbandk.Message.Companion<dev.pbkit.wrp.WrpHostMessageResStart> {
        public val defaultInstance: dev.pbkit.wrp.WrpHostMessageResStart by lazy { dev.pbkit.wrp.WrpHostMessageResStart() }
        override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.WrpHostMessageResStart = dev.pbkit.wrp.WrpHostMessageResStart.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpHostMessageResStart> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.WrpHostMessageResStart, *>>(2)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "req_id",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "reqId",
                        value = dev.pbkit.wrp.WrpHostMessageResStart::reqId
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "header",
                        number = 2,
                        type = pbandk.FieldDescriptor.Type.Map<String, String>(keyType = pbandk.FieldDescriptor.Type.Primitive.String(), valueType = pbandk.FieldDescriptor.Type.Primitive.String()),
                        jsonName = "header",
                        value = dev.pbkit.wrp.WrpHostMessageResStart::header
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpHostMessage_ResStart",
                messageClass = dev.pbkit.wrp.WrpHostMessageResStart::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }

    public data class HeaderEntry(
        override val key: String = "",
        override val value: String = "",
        override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
    ) : pbandk.Message, Map.Entry<String, String> {
        override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.WrpHostMessageResStart.HeaderEntry = protoMergeImpl(other)
        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpHostMessageResStart.HeaderEntry> get() = Companion.descriptor
        override val protoSize: Int by lazy { super.protoSize }
        public companion object : pbandk.Message.Companion<dev.pbkit.wrp.WrpHostMessageResStart.HeaderEntry> {
            public val defaultInstance: dev.pbkit.wrp.WrpHostMessageResStart.HeaderEntry by lazy { dev.pbkit.wrp.WrpHostMessageResStart.HeaderEntry() }
            override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.WrpHostMessageResStart.HeaderEntry = dev.pbkit.wrp.WrpHostMessageResStart.HeaderEntry.decodeWithImpl(u)

            override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpHostMessageResStart.HeaderEntry> by lazy {
                val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.WrpHostMessageResStart.HeaderEntry, *>>(2)
                fieldsList.apply {
                    add(
                        pbandk.FieldDescriptor(
                            messageDescriptor = this@Companion::descriptor,
                            name = "key",
                            number = 1,
                            type = pbandk.FieldDescriptor.Type.Primitive.String(),
                            jsonName = "key",
                            value = dev.pbkit.wrp.WrpHostMessageResStart.HeaderEntry::key
                        )
                    )
                    add(
                        pbandk.FieldDescriptor(
                            messageDescriptor = this@Companion::descriptor,
                            name = "value",
                            number = 2,
                            type = pbandk.FieldDescriptor.Type.Primitive.String(),
                            jsonName = "value",
                            value = dev.pbkit.wrp.WrpHostMessageResStart.HeaderEntry::value
                        )
                    )
                }
                pbandk.MessageDescriptor(
                    fullName = "pbkit.wrp.WrpHostMessage_ResStart.HeaderEntry",
                    messageClass = dev.pbkit.wrp.WrpHostMessageResStart.HeaderEntry::class,
                    messageCompanion = this,
                    fields = fieldsList
                )
            }
        }
    }
}

@pbandk.Export
public data class WrpHostMessageResPayload(
    val reqId: String = "",
    val payload: pbandk.ByteArr = pbandk.ByteArr.empty,
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.WrpHostMessageResPayload = protoMergeImpl(other)
    override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpHostMessageResPayload> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }
    public companion object : pbandk.Message.Companion<dev.pbkit.wrp.WrpHostMessageResPayload> {
        public val defaultInstance: dev.pbkit.wrp.WrpHostMessageResPayload by lazy { dev.pbkit.wrp.WrpHostMessageResPayload() }
        override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.WrpHostMessageResPayload = dev.pbkit.wrp.WrpHostMessageResPayload.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpHostMessageResPayload> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.WrpHostMessageResPayload, *>>(2)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "req_id",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "reqId",
                        value = dev.pbkit.wrp.WrpHostMessageResPayload::reqId
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "payload",
                        number = 2,
                        type = pbandk.FieldDescriptor.Type.Primitive.Bytes(),
                        jsonName = "payload",
                        value = dev.pbkit.wrp.WrpHostMessageResPayload::payload
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpHostMessage_ResPayload",
                messageClass = dev.pbkit.wrp.WrpHostMessageResPayload::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
public data class WrpHostMessageResFinish(
    val reqId: String = "",
    val trailer: Map<String, String> = emptyMap(),
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.WrpHostMessageResFinish = protoMergeImpl(other)
    override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpHostMessageResFinish> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }
    public companion object : pbandk.Message.Companion<dev.pbkit.wrp.WrpHostMessageResFinish> {
        public val defaultInstance: dev.pbkit.wrp.WrpHostMessageResFinish by lazy { dev.pbkit.wrp.WrpHostMessageResFinish() }
        override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.WrpHostMessageResFinish = dev.pbkit.wrp.WrpHostMessageResFinish.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpHostMessageResFinish> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.WrpHostMessageResFinish, *>>(2)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "req_id",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "reqId",
                        value = dev.pbkit.wrp.WrpHostMessageResFinish::reqId
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "trailer",
                        number = 2,
                        type = pbandk.FieldDescriptor.Type.Map<String, String>(keyType = pbandk.FieldDescriptor.Type.Primitive.String(), valueType = pbandk.FieldDescriptor.Type.Primitive.String()),
                        jsonName = "trailer",
                        value = dev.pbkit.wrp.WrpHostMessageResFinish::trailer
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpHostMessage_ResFinish",
                messageClass = dev.pbkit.wrp.WrpHostMessageResFinish::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }

    public data class TrailerEntry(
        override val key: String = "",
        override val value: String = "",
        override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
    ) : pbandk.Message, Map.Entry<String, String> {
        override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.WrpHostMessageResFinish.TrailerEntry = protoMergeImpl(other)
        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpHostMessageResFinish.TrailerEntry> get() = Companion.descriptor
        override val protoSize: Int by lazy { super.protoSize }
        public companion object : pbandk.Message.Companion<dev.pbkit.wrp.WrpHostMessageResFinish.TrailerEntry> {
            public val defaultInstance: dev.pbkit.wrp.WrpHostMessageResFinish.TrailerEntry by lazy { dev.pbkit.wrp.WrpHostMessageResFinish.TrailerEntry() }
            override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.WrpHostMessageResFinish.TrailerEntry = dev.pbkit.wrp.WrpHostMessageResFinish.TrailerEntry.decodeWithImpl(u)

            override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpHostMessageResFinish.TrailerEntry> by lazy {
                val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.WrpHostMessageResFinish.TrailerEntry, *>>(2)
                fieldsList.apply {
                    add(
                        pbandk.FieldDescriptor(
                            messageDescriptor = this@Companion::descriptor,
                            name = "key",
                            number = 1,
                            type = pbandk.FieldDescriptor.Type.Primitive.String(),
                            jsonName = "key",
                            value = dev.pbkit.wrp.WrpHostMessageResFinish.TrailerEntry::key
                        )
                    )
                    add(
                        pbandk.FieldDescriptor(
                            messageDescriptor = this@Companion::descriptor,
                            name = "value",
                            number = 2,
                            type = pbandk.FieldDescriptor.Type.Primitive.String(),
                            jsonName = "value",
                            value = dev.pbkit.wrp.WrpHostMessageResFinish.TrailerEntry::value
                        )
                    )
                }
                pbandk.MessageDescriptor(
                    fullName = "pbkit.wrp.WrpHostMessage_ResFinish.TrailerEntry",
                    messageClass = dev.pbkit.wrp.WrpHostMessageResFinish.TrailerEntry::class,
                    messageCompanion = this,
                    fields = fieldsList
                )
            }
        }
    }
}

@pbandk.Export
public data class WrpGuestMessageReqStart(
    val reqId: String = "",
    val methodName: String = "",
    val metadata: Map<String, String> = emptyMap(),
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.WrpGuestMessageReqStart = protoMergeImpl(other)
    override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpGuestMessageReqStart> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }
    public companion object : pbandk.Message.Companion<dev.pbkit.wrp.WrpGuestMessageReqStart> {
        public val defaultInstance: dev.pbkit.wrp.WrpGuestMessageReqStart by lazy { dev.pbkit.wrp.WrpGuestMessageReqStart() }
        override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.WrpGuestMessageReqStart = dev.pbkit.wrp.WrpGuestMessageReqStart.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpGuestMessageReqStart> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.WrpGuestMessageReqStart, *>>(3)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "req_id",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "reqId",
                        value = dev.pbkit.wrp.WrpGuestMessageReqStart::reqId
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "method_name",
                        number = 2,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "methodName",
                        value = dev.pbkit.wrp.WrpGuestMessageReqStart::methodName
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "metadata",
                        number = 3,
                        type = pbandk.FieldDescriptor.Type.Map<String, String>(keyType = pbandk.FieldDescriptor.Type.Primitive.String(), valueType = pbandk.FieldDescriptor.Type.Primitive.String()),
                        jsonName = "metadata",
                        value = dev.pbkit.wrp.WrpGuestMessageReqStart::metadata
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpGuestMessage_ReqStart",
                messageClass = dev.pbkit.wrp.WrpGuestMessageReqStart::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }

    public data class MetadataEntry(
        override val key: String = "",
        override val value: String = "",
        override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
    ) : pbandk.Message, Map.Entry<String, String> {
        override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.WrpGuestMessageReqStart.MetadataEntry = protoMergeImpl(other)
        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpGuestMessageReqStart.MetadataEntry> get() = Companion.descriptor
        override val protoSize: Int by lazy { super.protoSize }
        public companion object : pbandk.Message.Companion<dev.pbkit.wrp.WrpGuestMessageReqStart.MetadataEntry> {
            public val defaultInstance: dev.pbkit.wrp.WrpGuestMessageReqStart.MetadataEntry by lazy { dev.pbkit.wrp.WrpGuestMessageReqStart.MetadataEntry() }
            override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.WrpGuestMessageReqStart.MetadataEntry = dev.pbkit.wrp.WrpGuestMessageReqStart.MetadataEntry.decodeWithImpl(u)

            override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpGuestMessageReqStart.MetadataEntry> by lazy {
                val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.WrpGuestMessageReqStart.MetadataEntry, *>>(2)
                fieldsList.apply {
                    add(
                        pbandk.FieldDescriptor(
                            messageDescriptor = this@Companion::descriptor,
                            name = "key",
                            number = 1,
                            type = pbandk.FieldDescriptor.Type.Primitive.String(),
                            jsonName = "key",
                            value = dev.pbkit.wrp.WrpGuestMessageReqStart.MetadataEntry::key
                        )
                    )
                    add(
                        pbandk.FieldDescriptor(
                            messageDescriptor = this@Companion::descriptor,
                            name = "value",
                            number = 2,
                            type = pbandk.FieldDescriptor.Type.Primitive.String(),
                            jsonName = "value",
                            value = dev.pbkit.wrp.WrpGuestMessageReqStart.MetadataEntry::value
                        )
                    )
                }
                pbandk.MessageDescriptor(
                    fullName = "pbkit.wrp.WrpGuestMessage_ReqStart.MetadataEntry",
                    messageClass = dev.pbkit.wrp.WrpGuestMessageReqStart.MetadataEntry::class,
                    messageCompanion = this,
                    fields = fieldsList
                )
            }
        }
    }
}

@pbandk.Export
public data class WrpGuestMessageReqPayload(
    val reqId: String = "",
    val payload: pbandk.ByteArr = pbandk.ByteArr.empty,
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.WrpGuestMessageReqPayload = protoMergeImpl(other)
    override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpGuestMessageReqPayload> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }
    public companion object : pbandk.Message.Companion<dev.pbkit.wrp.WrpGuestMessageReqPayload> {
        public val defaultInstance: dev.pbkit.wrp.WrpGuestMessageReqPayload by lazy { dev.pbkit.wrp.WrpGuestMessageReqPayload() }
        override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.WrpGuestMessageReqPayload = dev.pbkit.wrp.WrpGuestMessageReqPayload.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpGuestMessageReqPayload> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.WrpGuestMessageReqPayload, *>>(2)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "req_id",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "reqId",
                        value = dev.pbkit.wrp.WrpGuestMessageReqPayload::reqId
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "payload",
                        number = 2,
                        type = pbandk.FieldDescriptor.Type.Primitive.Bytes(),
                        jsonName = "payload",
                        value = dev.pbkit.wrp.WrpGuestMessageReqPayload::payload
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpGuestMessage_ReqPayload",
                messageClass = dev.pbkit.wrp.WrpGuestMessageReqPayload::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
public data class WrpGuestMessageReqFinish(
    val reqId: String = "",
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.WrpGuestMessageReqFinish = protoMergeImpl(other)
    override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpGuestMessageReqFinish> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }
    public companion object : pbandk.Message.Companion<dev.pbkit.wrp.WrpGuestMessageReqFinish> {
        public val defaultInstance: dev.pbkit.wrp.WrpGuestMessageReqFinish by lazy { dev.pbkit.wrp.WrpGuestMessageReqFinish() }
        override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.WrpGuestMessageReqFinish = dev.pbkit.wrp.WrpGuestMessageReqFinish.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpGuestMessageReqFinish> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.WrpGuestMessageReqFinish, *>>(1)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "req_id",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "reqId",
                        value = dev.pbkit.wrp.WrpGuestMessageReqFinish::reqId
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpGuestMessage_ReqFinish",
                messageClass = dev.pbkit.wrp.WrpGuestMessageReqFinish::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
public data class WrpGuestMessageResFinish(
    val reqId: String = "",
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.WrpGuestMessageResFinish = protoMergeImpl(other)
    override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpGuestMessageResFinish> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }
    public companion object : pbandk.Message.Companion<dev.pbkit.wrp.WrpGuestMessageResFinish> {
        public val defaultInstance: dev.pbkit.wrp.WrpGuestMessageResFinish by lazy { dev.pbkit.wrp.WrpGuestMessageResFinish() }
        override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.WrpGuestMessageResFinish = dev.pbkit.wrp.WrpGuestMessageResFinish.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.WrpGuestMessageResFinish> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.WrpGuestMessageResFinish, *>>(1)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "req_id",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "reqId",
                        value = dev.pbkit.wrp.WrpGuestMessageResFinish::reqId
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpGuestMessage_ResFinish",
                messageClass = dev.pbkit.wrp.WrpGuestMessageResFinish::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpMessage")
public fun WrpMessage?.orDefault(): dev.pbkit.wrp.WrpMessage = this ?: WrpMessage.defaultInstance

private fun WrpMessage.protoMergeImpl(plus: pbandk.Message?): WrpMessage = (plus as? WrpMessage)?.let {
    it.copy(
        message = when {
            message is WrpMessage.Message.HostInitialize && plus.message is WrpMessage.Message.HostInitialize ->
                WrpMessage.Message.HostInitialize(message.value + plus.message.value)
            message is WrpMessage.Message.HostError && plus.message is WrpMessage.Message.HostError ->
                WrpMessage.Message.HostError(message.value + plus.message.value)
            message is WrpMessage.Message.HostResStart && plus.message is WrpMessage.Message.HostResStart ->
                WrpMessage.Message.HostResStart(message.value + plus.message.value)
            message is WrpMessage.Message.HostResPayload && plus.message is WrpMessage.Message.HostResPayload ->
                WrpMessage.Message.HostResPayload(message.value + plus.message.value)
            message is WrpMessage.Message.HostResFinish && plus.message is WrpMessage.Message.HostResFinish ->
                WrpMessage.Message.HostResFinish(message.value + plus.message.value)
            message is WrpMessage.Message.GuestReqStart && plus.message is WrpMessage.Message.GuestReqStart ->
                WrpMessage.Message.GuestReqStart(message.value + plus.message.value)
            message is WrpMessage.Message.GuestReqPayload && plus.message is WrpMessage.Message.GuestReqPayload ->
                WrpMessage.Message.GuestReqPayload(message.value + plus.message.value)
            message is WrpMessage.Message.GuestReqFinish && plus.message is WrpMessage.Message.GuestReqFinish ->
                WrpMessage.Message.GuestReqFinish(message.value + plus.message.value)
            message is WrpMessage.Message.GuestResFinish && plus.message is WrpMessage.Message.GuestResFinish ->
                WrpMessage.Message.GuestResFinish(message.value + plus.message.value)
            else ->
                plus.message ?: message
        },
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpMessage.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpMessage {
    var message: WrpMessage.Message<*>? = null

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> message = WrpMessage.Message.HostInitialize(_fieldValue as dev.pbkit.wrp.WrpHostMessageInitialize)
            2 -> message = WrpMessage.Message.HostError(_fieldValue as dev.pbkit.wrp.WrpHostMessageError)
            3 -> message = WrpMessage.Message.HostResStart(_fieldValue as dev.pbkit.wrp.WrpHostMessageResStart)
            4 -> message = WrpMessage.Message.HostResPayload(_fieldValue as dev.pbkit.wrp.WrpHostMessageResPayload)
            5 -> message = WrpMessage.Message.HostResFinish(_fieldValue as dev.pbkit.wrp.WrpHostMessageResFinish)
            6 -> message = WrpMessage.Message.GuestReqStart(_fieldValue as dev.pbkit.wrp.WrpGuestMessageReqStart)
            7 -> message = WrpMessage.Message.GuestReqPayload(_fieldValue as dev.pbkit.wrp.WrpGuestMessageReqPayload)
            8 -> message = WrpMessage.Message.GuestReqFinish(_fieldValue as dev.pbkit.wrp.WrpGuestMessageReqFinish)
            9 -> message = WrpMessage.Message.GuestResFinish(_fieldValue as dev.pbkit.wrp.WrpGuestMessageResFinish)
        }
    }
    return WrpMessage(message, unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpHostMessageInitialize")
public fun WrpHostMessageInitialize?.orDefault(): dev.pbkit.wrp.WrpHostMessageInitialize = this ?: WrpHostMessageInitialize.defaultInstance

private fun WrpHostMessageInitialize.protoMergeImpl(plus: pbandk.Message?): WrpHostMessageInitialize = (plus as? WrpHostMessageInitialize)?.let {
    it.copy(
        availableMethods = availableMethods + plus.availableMethods,
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpHostMessageInitialize.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpHostMessageInitialize {
    var availableMethods: pbandk.ListWithSize.Builder<String>? = null

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> availableMethods = (availableMethods ?: pbandk.ListWithSize.Builder()).apply { this += _fieldValue as Sequence<String> }
        }
    }
    return WrpHostMessageInitialize(pbandk.ListWithSize.Builder.fixed(availableMethods), unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpHostMessageError")
public fun WrpHostMessageError?.orDefault(): dev.pbkit.wrp.WrpHostMessageError = this ?: WrpHostMessageError.defaultInstance

private fun WrpHostMessageError.protoMergeImpl(plus: pbandk.Message?): WrpHostMessageError = (plus as? WrpHostMessageError)?.let {
    it.copy(
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpHostMessageError.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpHostMessageError {
    var message = ""

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> message = _fieldValue as String
        }
    }
    return WrpHostMessageError(message, unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpHostMessageResStart")
public fun WrpHostMessageResStart?.orDefault(): dev.pbkit.wrp.WrpHostMessageResStart = this ?: WrpHostMessageResStart.defaultInstance

private fun WrpHostMessageResStart.protoMergeImpl(plus: pbandk.Message?): WrpHostMessageResStart = (plus as? WrpHostMessageResStart)?.let {
    it.copy(
        header = header + plus.header,
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpHostMessageResStart.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpHostMessageResStart {
    var reqId = ""
    var header: pbandk.MessageMap.Builder<String, String>? = null

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> reqId = _fieldValue as String
            2 -> header = (header ?: pbandk.MessageMap.Builder()).apply { this.entries += _fieldValue as Sequence<pbandk.MessageMap.Entry<String, String>> }
        }
    }
    return WrpHostMessageResStart(reqId, pbandk.MessageMap.Builder.fixed(header), unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpHostMessageResStartHeaderEntry")
public fun WrpHostMessageResStart.HeaderEntry?.orDefault(): dev.pbkit.wrp.WrpHostMessageResStart.HeaderEntry = this ?: WrpHostMessageResStart.HeaderEntry.defaultInstance

private fun WrpHostMessageResStart.HeaderEntry.protoMergeImpl(plus: pbandk.Message?): WrpHostMessageResStart.HeaderEntry = (plus as? WrpHostMessageResStart.HeaderEntry)?.let {
    it.copy(
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpHostMessageResStart.HeaderEntry.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpHostMessageResStart.HeaderEntry {
    var key = ""
    var value = ""

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> key = _fieldValue as String
            2 -> value = _fieldValue as String
        }
    }
    return WrpHostMessageResStart.HeaderEntry(key, value, unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpHostMessageResPayload")
public fun WrpHostMessageResPayload?.orDefault(): dev.pbkit.wrp.WrpHostMessageResPayload = this ?: WrpHostMessageResPayload.defaultInstance

private fun WrpHostMessageResPayload.protoMergeImpl(plus: pbandk.Message?): WrpHostMessageResPayload = (plus as? WrpHostMessageResPayload)?.let {
    it.copy(
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpHostMessageResPayload.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpHostMessageResPayload {
    var reqId = ""
    var payload: pbandk.ByteArr = pbandk.ByteArr.empty

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> reqId = _fieldValue as String
            2 -> payload = _fieldValue as pbandk.ByteArr
        }
    }
    return WrpHostMessageResPayload(reqId, payload, unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpHostMessageResFinish")
public fun WrpHostMessageResFinish?.orDefault(): dev.pbkit.wrp.WrpHostMessageResFinish = this ?: WrpHostMessageResFinish.defaultInstance

private fun WrpHostMessageResFinish.protoMergeImpl(plus: pbandk.Message?): WrpHostMessageResFinish = (plus as? WrpHostMessageResFinish)?.let {
    it.copy(
        trailer = trailer + plus.trailer,
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpHostMessageResFinish.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpHostMessageResFinish {
    var reqId = ""
    var trailer: pbandk.MessageMap.Builder<String, String>? = null

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> reqId = _fieldValue as String
            2 -> trailer = (trailer ?: pbandk.MessageMap.Builder()).apply { this.entries += _fieldValue as Sequence<pbandk.MessageMap.Entry<String, String>> }
        }
    }
    return WrpHostMessageResFinish(reqId, pbandk.MessageMap.Builder.fixed(trailer), unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpHostMessageResFinishTrailerEntry")
public fun WrpHostMessageResFinish.TrailerEntry?.orDefault(): dev.pbkit.wrp.WrpHostMessageResFinish.TrailerEntry = this ?: WrpHostMessageResFinish.TrailerEntry.defaultInstance

private fun WrpHostMessageResFinish.TrailerEntry.protoMergeImpl(plus: pbandk.Message?): WrpHostMessageResFinish.TrailerEntry = (plus as? WrpHostMessageResFinish.TrailerEntry)?.let {
    it.copy(
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpHostMessageResFinish.TrailerEntry.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpHostMessageResFinish.TrailerEntry {
    var key = ""
    var value = ""

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> key = _fieldValue as String
            2 -> value = _fieldValue as String
        }
    }
    return WrpHostMessageResFinish.TrailerEntry(key, value, unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpGuestMessageReqStart")
public fun WrpGuestMessageReqStart?.orDefault(): dev.pbkit.wrp.WrpGuestMessageReqStart = this ?: WrpGuestMessageReqStart.defaultInstance

private fun WrpGuestMessageReqStart.protoMergeImpl(plus: pbandk.Message?): WrpGuestMessageReqStart = (plus as? WrpGuestMessageReqStart)?.let {
    it.copy(
        metadata = metadata + plus.metadata,
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpGuestMessageReqStart.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpGuestMessageReqStart {
    var reqId = ""
    var methodName = ""
    var metadata: pbandk.MessageMap.Builder<String, String>? = null

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> reqId = _fieldValue as String
            2 -> methodName = _fieldValue as String
            3 -> metadata = (metadata ?: pbandk.MessageMap.Builder()).apply { this.entries += _fieldValue as Sequence<pbandk.MessageMap.Entry<String, String>> }
        }
    }
    return WrpGuestMessageReqStart(reqId, methodName, pbandk.MessageMap.Builder.fixed(metadata), unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpGuestMessageReqStartMetadataEntry")
public fun WrpGuestMessageReqStart.MetadataEntry?.orDefault(): dev.pbkit.wrp.WrpGuestMessageReqStart.MetadataEntry = this ?: WrpGuestMessageReqStart.MetadataEntry.defaultInstance

private fun WrpGuestMessageReqStart.MetadataEntry.protoMergeImpl(plus: pbandk.Message?): WrpGuestMessageReqStart.MetadataEntry = (plus as? WrpGuestMessageReqStart.MetadataEntry)?.let {
    it.copy(
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpGuestMessageReqStart.MetadataEntry.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpGuestMessageReqStart.MetadataEntry {
    var key = ""
    var value = ""

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> key = _fieldValue as String
            2 -> value = _fieldValue as String
        }
    }
    return WrpGuestMessageReqStart.MetadataEntry(key, value, unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpGuestMessageReqPayload")
public fun WrpGuestMessageReqPayload?.orDefault(): dev.pbkit.wrp.WrpGuestMessageReqPayload = this ?: WrpGuestMessageReqPayload.defaultInstance

private fun WrpGuestMessageReqPayload.protoMergeImpl(plus: pbandk.Message?): WrpGuestMessageReqPayload = (plus as? WrpGuestMessageReqPayload)?.let {
    it.copy(
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpGuestMessageReqPayload.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpGuestMessageReqPayload {
    var reqId = ""
    var payload: pbandk.ByteArr = pbandk.ByteArr.empty

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> reqId = _fieldValue as String
            2 -> payload = _fieldValue as pbandk.ByteArr
        }
    }
    return WrpGuestMessageReqPayload(reqId, payload, unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpGuestMessageReqFinish")
public fun WrpGuestMessageReqFinish?.orDefault(): dev.pbkit.wrp.WrpGuestMessageReqFinish = this ?: WrpGuestMessageReqFinish.defaultInstance

private fun WrpGuestMessageReqFinish.protoMergeImpl(plus: pbandk.Message?): WrpGuestMessageReqFinish = (plus as? WrpGuestMessageReqFinish)?.let {
    it.copy(
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpGuestMessageReqFinish.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpGuestMessageReqFinish {
    var reqId = ""

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> reqId = _fieldValue as String
        }
    }
    return WrpGuestMessageReqFinish(reqId, unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpGuestMessageResFinish")
public fun WrpGuestMessageResFinish?.orDefault(): dev.pbkit.wrp.WrpGuestMessageResFinish = this ?: WrpGuestMessageResFinish.defaultInstance

private fun WrpGuestMessageResFinish.protoMergeImpl(plus: pbandk.Message?): WrpGuestMessageResFinish = (plus as? WrpGuestMessageResFinish)?.let {
    it.copy(
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpGuestMessageResFinish.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpGuestMessageResFinish {
    var reqId = ""

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> reqId = _fieldValue as String
        }
    }
    return WrpGuestMessageResFinish(reqId, unknownFields)
}
