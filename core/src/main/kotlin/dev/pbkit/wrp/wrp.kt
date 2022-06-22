@file:OptIn(pbandk.PublicForGeneratedCode::class)

package dev.pbkit.wrp

@pbandk.Export
data class WrpMessage(
    val message: Message<*>? = null,
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    sealed class Message<V>(value: V) : pbandk.Message.OneOf<V>(value) {
        class HostInitialize(hostInitialize: WrpHostMessageInitialize) :
            Message<WrpHostMessageInitialize>(hostInitialize)

        class HostError(hostError: WrpHostMessageError) :
            Message<WrpHostMessageError>(hostError)

        class HostResStart(hostResStart: WrpHostMessageResStart) :
            Message<WrpHostMessageResStart>(hostResStart)

        class HostResPayload(hostResPayload: WrpHostMessageResPayload) :
            Message<WrpHostMessageResPayload>(hostResPayload)

        class HostResFinish(hostResFinish: WrpHostMessageResFinish) :
            Message<WrpHostMessageResFinish>(hostResFinish)

        class GuestReqStart(guestReqStart: WrpGuestMessageReqStart) :
            Message<WrpGuestMessageReqStart>(guestReqStart)

        class GuestReqPayload(guestReqPayload: WrpGuestMessageReqPayload) :
            Message<WrpGuestMessageReqPayload>(guestReqPayload)

        class GuestReqFinish(guestReqFinish: WrpGuestMessageReqFinish) :
            Message<WrpGuestMessageReqFinish>(guestReqFinish)

        class GuestResFinish(guestResFinish: WrpGuestMessageResFinish) :
            Message<WrpGuestMessageResFinish>(guestResFinish)
    }

    val hostInitialize: WrpHostMessageInitialize?
        get() = (message as? Message.HostInitialize)?.value
    val hostError: WrpHostMessageError?
        get() = (message as? Message.HostError)?.value
    val hostResStart: WrpHostMessageResStart?
        get() = (message as? Message.HostResStart)?.value
    val hostResPayload: WrpHostMessageResPayload?
        get() = (message as? Message.HostResPayload)?.value
    val hostResFinish: WrpHostMessageResFinish?
        get() = (message as? Message.HostResFinish)?.value
    val guestReqStart: WrpGuestMessageReqStart?
        get() = (message as? Message.GuestReqStart)?.value
    val guestReqPayload: WrpGuestMessageReqPayload?
        get() = (message as? Message.GuestReqPayload)?.value
    val guestReqFinish: WrpGuestMessageReqFinish?
        get() = (message as? Message.GuestReqFinish)?.value
    val guestResFinish: WrpGuestMessageResFinish?
        get() = (message as? Message.GuestResFinish)?.value

    override operator fun plus(other: pbandk.Message?): WrpMessage =
        protoMergeImpl(other)

    override val descriptor: pbandk.MessageDescriptor<WrpMessage> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }

    companion object : pbandk.Message.Companion<WrpMessage> {
        val defaultInstance: WrpMessage by lazy { WrpMessage() }
        override fun decodeWith(u: pbandk.MessageDecoder): WrpMessage =
            WrpMessage.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<WrpMessage> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<WrpMessage, *>>(9)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Host_Initialize",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = WrpHostMessageInitialize.Companion),
                        oneofMember = true,
                        jsonName = "HostInitialize",
                        value = WrpMessage::hostInitialize
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Host_Error",
                        number = 2,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = WrpHostMessageError.Companion),
                        oneofMember = true,
                        jsonName = "HostError",
                        value = WrpMessage::hostError
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Host_ResStart",
                        number = 3,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = WrpHostMessageResStart.Companion),
                        oneofMember = true,
                        jsonName = "HostResStart",
                        value = WrpMessage::hostResStart
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Host_ResPayload",
                        number = 4,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = WrpHostMessageResPayload.Companion),
                        oneofMember = true,
                        jsonName = "HostResPayload",
                        value = WrpMessage::hostResPayload
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Host_ResFinish",
                        number = 5,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = WrpHostMessageResFinish.Companion),
                        oneofMember = true,
                        jsonName = "HostResFinish",
                        value = WrpMessage::hostResFinish
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Guest_ReqStart",
                        number = 6,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = WrpGuestMessageReqStart.Companion),
                        oneofMember = true,
                        jsonName = "GuestReqStart",
                        value = WrpMessage::guestReqStart
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Guest_ReqPayload",
                        number = 7,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = WrpGuestMessageReqPayload.Companion),
                        oneofMember = true,
                        jsonName = "GuestReqPayload",
                        value = WrpMessage::guestReqPayload
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Guest_ReqFinish",
                        number = 8,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = WrpGuestMessageReqFinish.Companion),
                        oneofMember = true,
                        jsonName = "GuestReqFinish",
                        value = WrpMessage::guestReqFinish
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "Guest_ResFinish",
                        number = 9,
                        type = pbandk.FieldDescriptor.Type.Message(messageCompanion = WrpGuestMessageResFinish.Companion),
                        oneofMember = true,
                        jsonName = "GuestResFinish",
                        value = WrpMessage::guestResFinish
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpMessage",
                messageClass = WrpMessage::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
data class WrpHostMessageInitialize(
    val availableMethods: List<String> = emptyList(),
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): WrpHostMessageInitialize =
        protoMergeImpl(other)

    override val descriptor: pbandk.MessageDescriptor<WrpHostMessageInitialize> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }

    companion object : pbandk.Message.Companion<WrpHostMessageInitialize> {
        val defaultInstance: WrpHostMessageInitialize by lazy { WrpHostMessageInitialize() }
        override fun decodeWith(u: pbandk.MessageDecoder): WrpHostMessageInitialize =
            WrpHostMessageInitialize.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<WrpHostMessageInitialize> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<WrpHostMessageInitialize, *>>(1)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "available_methods",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Repeated<String>(valueType = pbandk.FieldDescriptor.Type.Primitive.String()),
                        jsonName = "availableMethods",
                        value = WrpHostMessageInitialize::availableMethods
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpHostMessage_Initialize",
                messageClass = WrpHostMessageInitialize::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
data class WrpHostMessageError(
    val message: String = "",
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): WrpHostMessageError = protoMergeImpl(other)
    override val descriptor: pbandk.MessageDescriptor<WrpHostMessageError> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }

    companion object : pbandk.Message.Companion<WrpHostMessageError> {
        val defaultInstance: WrpHostMessageError by lazy { WrpHostMessageError() }
        override fun decodeWith(u: pbandk.MessageDecoder): WrpHostMessageError =
            WrpHostMessageError.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<WrpHostMessageError> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<WrpHostMessageError, *>>(1)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "message",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "message",
                        value = WrpHostMessageError::message
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpHostMessage_Error",
                messageClass = WrpHostMessageError::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
data class WrpHostMessageResStart(
    val reqId: String = "",
    val header: Map<String, String> = emptyMap(),
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): WrpHostMessageResStart =
        protoMergeImpl(other)

    override val descriptor: pbandk.MessageDescriptor<WrpHostMessageResStart> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }

    companion object : pbandk.Message.Companion<WrpHostMessageResStart> {
        val defaultInstance: WrpHostMessageResStart by lazy { WrpHostMessageResStart() }
        override fun decodeWith(u: pbandk.MessageDecoder): WrpHostMessageResStart =
            WrpHostMessageResStart.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<WrpHostMessageResStart> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<WrpHostMessageResStart, *>>(2)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "req_id",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "reqId",
                        value = WrpHostMessageResStart::reqId
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "header",
                        number = 2,
                        type = pbandk.FieldDescriptor.Type.Map<String, String>(
                            keyType = pbandk.FieldDescriptor.Type.Primitive.String(),
                            valueType = pbandk.FieldDescriptor.Type.Primitive.String()
                        ),
                        jsonName = "header",
                        value = WrpHostMessageResStart::header
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpHostMessage_ResStart",
                messageClass = WrpHostMessageResStart::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }

    data class HeaderEntry(
        override val key: String = "",
        override val value: String = "",
        override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
    ) : pbandk.Message, Map.Entry<String, String> {
        override operator fun plus(other: pbandk.Message?): HeaderEntry =
            protoMergeImpl(other)

        override val descriptor: pbandk.MessageDescriptor<HeaderEntry> get() = Companion.descriptor
        override val protoSize: Int by lazy { super.protoSize }

        companion object : pbandk.Message.Companion<HeaderEntry> {
            val defaultInstance: HeaderEntry by lazy { HeaderEntry() }
            override fun decodeWith(u: pbandk.MessageDecoder): HeaderEntry =
                HeaderEntry.decodeWithImpl(u)

            override val descriptor: pbandk.MessageDescriptor<HeaderEntry> by lazy {
                val fieldsList =
                    ArrayList<pbandk.FieldDescriptor<HeaderEntry, *>>(2)
                fieldsList.apply {
                    add(
                        pbandk.FieldDescriptor(
                            messageDescriptor = this@Companion::descriptor,
                            name = "key",
                            number = 1,
                            type = pbandk.FieldDescriptor.Type.Primitive.String(),
                            jsonName = "key",
                            value = HeaderEntry::key
                        )
                    )
                    add(
                        pbandk.FieldDescriptor(
                            messageDescriptor = this@Companion::descriptor,
                            name = "value",
                            number = 2,
                            type = pbandk.FieldDescriptor.Type.Primitive.String(),
                            jsonName = "value",
                            value = HeaderEntry::value
                        )
                    )
                }
                pbandk.MessageDescriptor(
                    fullName = "pbkit.wrp.WrpHostMessage_ResStart.HeaderEntry",
                    messageClass = HeaderEntry::class,
                    messageCompanion = this,
                    fields = fieldsList
                )
            }
        }
    }
}

@pbandk.Export
data class WrpHostMessageResPayload(
    val reqId: String = "",
    val payload: pbandk.ByteArr = pbandk.ByteArr.empty,
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): WrpHostMessageResPayload =
        protoMergeImpl(other)

    override val descriptor: pbandk.MessageDescriptor<WrpHostMessageResPayload> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }

    companion object : pbandk.Message.Companion<WrpHostMessageResPayload> {
        val defaultInstance: WrpHostMessageResPayload by lazy { WrpHostMessageResPayload() }
        override fun decodeWith(u: pbandk.MessageDecoder): WrpHostMessageResPayload =
            WrpHostMessageResPayload.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<WrpHostMessageResPayload> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<WrpHostMessageResPayload, *>>(2)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "req_id",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "reqId",
                        value = WrpHostMessageResPayload::reqId
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "payload",
                        number = 2,
                        type = pbandk.FieldDescriptor.Type.Primitive.Bytes(),
                        jsonName = "payload",
                        value = WrpHostMessageResPayload::payload
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpHostMessage_ResPayload",
                messageClass = WrpHostMessageResPayload::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
data class WrpHostMessageResFinish(
    val reqId: String = "",
    val trailer: Map<String, String> = emptyMap(),
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): WrpHostMessageResFinish =
        protoMergeImpl(other)

    override val descriptor: pbandk.MessageDescriptor<WrpHostMessageResFinish> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }

    companion object : pbandk.Message.Companion<WrpHostMessageResFinish> {
        val defaultInstance: WrpHostMessageResFinish by lazy { WrpHostMessageResFinish() }
        override fun decodeWith(u: pbandk.MessageDecoder): WrpHostMessageResFinish =
            WrpHostMessageResFinish.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<WrpHostMessageResFinish> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<WrpHostMessageResFinish, *>>(2)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "req_id",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "reqId",
                        value = WrpHostMessageResFinish::reqId
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "trailer",
                        number = 2,
                        type = pbandk.FieldDescriptor.Type.Map<String, String>(
                            keyType = pbandk.FieldDescriptor.Type.Primitive.String(),
                            valueType = pbandk.FieldDescriptor.Type.Primitive.String()
                        ),
                        jsonName = "trailer",
                        value = WrpHostMessageResFinish::trailer
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpHostMessage_ResFinish",
                messageClass = WrpHostMessageResFinish::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }

    data class TrailerEntry(
        override val key: String = "",
        override val value: String = "",
        override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
    ) : pbandk.Message, Map.Entry<String, String> {
        override operator fun plus(other: pbandk.Message?): TrailerEntry =
            protoMergeImpl(other)

        override val descriptor: pbandk.MessageDescriptor<TrailerEntry> get() = Companion.descriptor
        override val protoSize: Int by lazy { super.protoSize }

        companion object : pbandk.Message.Companion<TrailerEntry> {
            val defaultInstance: TrailerEntry by lazy { TrailerEntry() }
            override fun decodeWith(u: pbandk.MessageDecoder): TrailerEntry =
                TrailerEntry.decodeWithImpl(u)

            override val descriptor: pbandk.MessageDescriptor<TrailerEntry> by lazy {
                val fieldsList =
                    ArrayList<pbandk.FieldDescriptor<TrailerEntry, *>>(2)
                fieldsList.apply {
                    add(
                        pbandk.FieldDescriptor(
                            messageDescriptor = this@Companion::descriptor,
                            name = "key",
                            number = 1,
                            type = pbandk.FieldDescriptor.Type.Primitive.String(),
                            jsonName = "key",
                            value = TrailerEntry::key
                        )
                    )
                    add(
                        pbandk.FieldDescriptor(
                            messageDescriptor = this@Companion::descriptor,
                            name = "value",
                            number = 2,
                            type = pbandk.FieldDescriptor.Type.Primitive.String(),
                            jsonName = "value",
                            value = TrailerEntry::value
                        )
                    )
                }
                pbandk.MessageDescriptor(
                    fullName = "pbkit.wrp.WrpHostMessage_ResFinish.TrailerEntry",
                    messageClass = TrailerEntry::class,
                    messageCompanion = this,
                    fields = fieldsList
                )
            }
        }
    }
}

@pbandk.Export
data class WrpGuestMessageReqStart(
    val reqId: String = "",
    val methodName: String = "",
    val metadata: Map<String, String> = emptyMap(),
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): WrpGuestMessageReqStart =
        protoMergeImpl(other)

    override val descriptor: pbandk.MessageDescriptor<WrpGuestMessageReqStart> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }

    companion object : pbandk.Message.Companion<WrpGuestMessageReqStart> {
        val defaultInstance: WrpGuestMessageReqStart by lazy { WrpGuestMessageReqStart() }
        override fun decodeWith(u: pbandk.MessageDecoder): WrpGuestMessageReqStart =
            WrpGuestMessageReqStart.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<WrpGuestMessageReqStart> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<WrpGuestMessageReqStart, *>>(3)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "req_id",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "reqId",
                        value = WrpGuestMessageReqStart::reqId
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "method_name",
                        number = 2,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "methodName",
                        value = WrpGuestMessageReqStart::methodName
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "metadata",
                        number = 3,
                        type = pbandk.FieldDescriptor.Type.Map<String, String>(
                            keyType = pbandk.FieldDescriptor.Type.Primitive.String(),
                            valueType = pbandk.FieldDescriptor.Type.Primitive.String()
                        ),
                        jsonName = "metadata",
                        value = WrpGuestMessageReqStart::metadata
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpGuestMessage_ReqStart",
                messageClass = WrpGuestMessageReqStart::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }

    data class MetadataEntry(
        override val key: String = "",
        override val value: String = "",
        override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
    ) : pbandk.Message, Map.Entry<String, String> {
        override operator fun plus(other: pbandk.Message?): MetadataEntry =
            protoMergeImpl(other)

        override val descriptor: pbandk.MessageDescriptor<MetadataEntry> get() = Companion.descriptor
        override val protoSize: Int by lazy { super.protoSize }

        companion object : pbandk.Message.Companion<MetadataEntry> {
            val defaultInstance: MetadataEntry by lazy { MetadataEntry() }
            override fun decodeWith(u: pbandk.MessageDecoder): MetadataEntry =
                MetadataEntry.decodeWithImpl(u)

            override val descriptor: pbandk.MessageDescriptor<MetadataEntry> by lazy {
                val fieldsList =
                    ArrayList<pbandk.FieldDescriptor<MetadataEntry, *>>(2)
                fieldsList.apply {
                    add(
                        pbandk.FieldDescriptor(
                            messageDescriptor = this@Companion::descriptor,
                            name = "key",
                            number = 1,
                            type = pbandk.FieldDescriptor.Type.Primitive.String(),
                            jsonName = "key",
                            value = MetadataEntry::key
                        )
                    )
                    add(
                        pbandk.FieldDescriptor(
                            messageDescriptor = this@Companion::descriptor,
                            name = "value",
                            number = 2,
                            type = pbandk.FieldDescriptor.Type.Primitive.String(),
                            jsonName = "value",
                            value = MetadataEntry::value
                        )
                    )
                }
                pbandk.MessageDescriptor(
                    fullName = "pbkit.wrp.WrpGuestMessage_ReqStart.MetadataEntry",
                    messageClass = MetadataEntry::class,
                    messageCompanion = this,
                    fields = fieldsList
                )
            }
        }
    }
}

@pbandk.Export
data class WrpGuestMessageReqPayload(
    val reqId: String = "",
    val payload: pbandk.ByteArr = pbandk.ByteArr.empty,
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): WrpGuestMessageReqPayload =
        protoMergeImpl(other)

    override val descriptor: pbandk.MessageDescriptor<WrpGuestMessageReqPayload> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }

    companion object : pbandk.Message.Companion<WrpGuestMessageReqPayload> {
        val defaultInstance: WrpGuestMessageReqPayload by lazy { WrpGuestMessageReqPayload() }
        override fun decodeWith(u: pbandk.MessageDecoder): WrpGuestMessageReqPayload =
            WrpGuestMessageReqPayload.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<WrpGuestMessageReqPayload> by lazy {
            val fieldsList =
                ArrayList<pbandk.FieldDescriptor<WrpGuestMessageReqPayload, *>>(2)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "req_id",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "reqId",
                        value = WrpGuestMessageReqPayload::reqId
                    )
                )
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "payload",
                        number = 2,
                        type = pbandk.FieldDescriptor.Type.Primitive.Bytes(),
                        jsonName = "payload",
                        value = WrpGuestMessageReqPayload::payload
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpGuestMessage_ReqPayload",
                messageClass = WrpGuestMessageReqPayload::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
data class WrpGuestMessageReqFinish(
    val reqId: String = "",
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): WrpGuestMessageReqFinish =
        protoMergeImpl(other)

    override val descriptor: pbandk.MessageDescriptor<WrpGuestMessageReqFinish> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }

    companion object : pbandk.Message.Companion<WrpGuestMessageReqFinish> {
        val defaultInstance: WrpGuestMessageReqFinish by lazy { WrpGuestMessageReqFinish() }
        override fun decodeWith(u: pbandk.MessageDecoder): WrpGuestMessageReqFinish =
            WrpGuestMessageReqFinish.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<WrpGuestMessageReqFinish> by lazy {
            val fieldsList =
                ArrayList<pbandk.FieldDescriptor<WrpGuestMessageReqFinish, *>>(1)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "req_id",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "reqId",
                        value = WrpGuestMessageReqFinish::reqId
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpGuestMessage_ReqFinish",
                messageClass = WrpGuestMessageReqFinish::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
data class WrpGuestMessageResFinish(
    val reqId: String = "",
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): WrpGuestMessageResFinish =
        protoMergeImpl(other)

    override val descriptor: pbandk.MessageDescriptor<WrpGuestMessageResFinish> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }

    companion object : pbandk.Message.Companion<WrpGuestMessageResFinish> {
        val defaultInstance: WrpGuestMessageResFinish by lazy { WrpGuestMessageResFinish() }
        override fun decodeWith(u: pbandk.MessageDecoder): WrpGuestMessageResFinish =
            WrpGuestMessageResFinish.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<WrpGuestMessageResFinish> by lazy {
            val fieldsList =
                ArrayList<pbandk.FieldDescriptor<WrpGuestMessageResFinish, *>>(1)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "req_id",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "reqId",
                        value = WrpGuestMessageResFinish::reqId
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.WrpGuestMessage_ResFinish",
                messageClass = WrpGuestMessageResFinish::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpMessage")
fun WrpMessage?.orDefault(): WrpMessage = this ?: WrpMessage.defaultInstance

private fun WrpMessage.protoMergeImpl(plus: pbandk.Message?): WrpMessage =
    (plus as? WrpMessage)?.copy(
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
    ) ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpMessage.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpMessage {
    var message: WrpMessage.Message<*>? = null

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> message =
                WrpMessage.Message.HostInitialize(_fieldValue as WrpHostMessageInitialize)
            2 -> message = WrpMessage.Message.HostError(_fieldValue as WrpHostMessageError)
            3 -> message = WrpMessage.Message.HostResStart(_fieldValue as WrpHostMessageResStart)
            4 -> message =
                WrpMessage.Message.HostResPayload(_fieldValue as WrpHostMessageResPayload)
            5 -> message = WrpMessage.Message.HostResFinish(_fieldValue as WrpHostMessageResFinish)
            6 -> message = WrpMessage.Message.GuestReqStart(_fieldValue as WrpGuestMessageReqStart)
            7 -> message =
                WrpMessage.Message.GuestReqPayload(_fieldValue as WrpGuestMessageReqPayload)
            8 -> message =
                WrpMessage.Message.GuestReqFinish(_fieldValue as WrpGuestMessageReqFinish)
            9 -> message =
                WrpMessage.Message.GuestResFinish(_fieldValue as WrpGuestMessageResFinish)
        }
    }
    return WrpMessage(message, unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpHostMessageInitialize")
fun WrpHostMessageInitialize?.orDefault(): WrpHostMessageInitialize =
    this ?: WrpHostMessageInitialize.defaultInstance

private fun WrpHostMessageInitialize.protoMergeImpl(plus: pbandk.Message?): WrpHostMessageInitialize =
    (plus as? WrpHostMessageInitialize)?.let {
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
            1 -> availableMethods = (availableMethods
                ?: pbandk.ListWithSize.Builder()).apply { this += _fieldValue as Sequence<String> }
        }
    }
    return WrpHostMessageInitialize(
        pbandk.ListWithSize.Builder.fixed(availableMethods),
        unknownFields
    )
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpHostMessageError")
fun WrpHostMessageError?.orDefault(): WrpHostMessageError =
    this ?: WrpHostMessageError.defaultInstance

private fun WrpHostMessageError.protoMergeImpl(plus: pbandk.Message?): WrpHostMessageError =
    (plus as? WrpHostMessageError)?.copy(
        unknownFields = unknownFields + plus.unknownFields
    ) ?: this

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
fun WrpHostMessageResStart?.orDefault(): WrpHostMessageResStart =
    this ?: WrpHostMessageResStart.defaultInstance

private fun WrpHostMessageResStart.protoMergeImpl(plus: pbandk.Message?): WrpHostMessageResStart =
    (plus as? WrpHostMessageResStart)?.copy(
        header = header + plus.header,
        unknownFields = unknownFields + plus.unknownFields
    ) ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpHostMessageResStart.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpHostMessageResStart {
    var reqId = ""
    var header: pbandk.MessageMap.Builder<String, String>? = null

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> reqId = _fieldValue as String
            2 -> header = (header
                ?: pbandk.MessageMap.Builder()).apply { this.entries += _fieldValue as Sequence<pbandk.MessageMap.Entry<String, String>> }
        }
    }
    return WrpHostMessageResStart(reqId, pbandk.MessageMap.Builder.fixed(header), unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpHostMessageResStartHeaderEntry")
fun WrpHostMessageResStart.HeaderEntry?.orDefault(): WrpHostMessageResStart.HeaderEntry =
    this ?: WrpHostMessageResStart.HeaderEntry.defaultInstance

private fun WrpHostMessageResStart.HeaderEntry.protoMergeImpl(plus: pbandk.Message?): WrpHostMessageResStart.HeaderEntry =
    (plus as? WrpHostMessageResStart.HeaderEntry)?.copy(
        unknownFields = unknownFields + plus.unknownFields
    ) ?: this

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
fun WrpHostMessageResPayload?.orDefault(): WrpHostMessageResPayload =
    this ?: WrpHostMessageResPayload.defaultInstance

private fun WrpHostMessageResPayload.protoMergeImpl(plus: pbandk.Message?): WrpHostMessageResPayload =
    (plus as? WrpHostMessageResPayload)?.copy(
        unknownFields = unknownFields + plus.unknownFields
    ) ?: this

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
fun WrpHostMessageResFinish?.orDefault(): WrpHostMessageResFinish =
    this ?: WrpHostMessageResFinish.defaultInstance

private fun WrpHostMessageResFinish.protoMergeImpl(plus: pbandk.Message?): WrpHostMessageResFinish =
    (plus as? WrpHostMessageResFinish)?.copy(
        trailer = trailer + plus.trailer,
        unknownFields = unknownFields + plus.unknownFields
    ) ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpHostMessageResFinish.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpHostMessageResFinish {
    var reqId = ""
    var trailer: pbandk.MessageMap.Builder<String, String>? = null

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> reqId = _fieldValue as String
            2 -> trailer = (trailer
                ?: pbandk.MessageMap.Builder()).apply { this.entries += _fieldValue as Sequence<pbandk.MessageMap.Entry<String, String>> }
        }
    }
    return WrpHostMessageResFinish(reqId, pbandk.MessageMap.Builder.fixed(trailer), unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpHostMessageResFinishTrailerEntry")
fun WrpHostMessageResFinish.TrailerEntry?.orDefault(): WrpHostMessageResFinish.TrailerEntry =
    this ?: WrpHostMessageResFinish.TrailerEntry.defaultInstance

private fun WrpHostMessageResFinish.TrailerEntry.protoMergeImpl(plus: pbandk.Message?): WrpHostMessageResFinish.TrailerEntry =
    (plus as? WrpHostMessageResFinish.TrailerEntry)?.copy(
        unknownFields = unknownFields + plus.unknownFields
    ) ?: this

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
fun WrpGuestMessageReqStart?.orDefault(): WrpGuestMessageReqStart =
    this ?: WrpGuestMessageReqStart.defaultInstance

private fun WrpGuestMessageReqStart.protoMergeImpl(plus: pbandk.Message?): WrpGuestMessageReqStart =
    (plus as? WrpGuestMessageReqStart)?.copy(
        metadata = metadata + plus.metadata,
        unknownFields = unknownFields + plus.unknownFields
    ) ?: this

@Suppress("UNCHECKED_CAST")
private fun WrpGuestMessageReqStart.Companion.decodeWithImpl(u: pbandk.MessageDecoder): WrpGuestMessageReqStart {
    var reqId = ""
    var methodName = ""
    var metadata: pbandk.MessageMap.Builder<String, String>? = null

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> reqId = _fieldValue as String
            2 -> methodName = _fieldValue as String
            3 -> metadata = (metadata
                ?: pbandk.MessageMap.Builder()).apply { this.entries += _fieldValue as Sequence<pbandk.MessageMap.Entry<String, String>> }
        }
    }
    return WrpGuestMessageReqStart(
        reqId,
        methodName,
        pbandk.MessageMap.Builder.fixed(metadata),
        unknownFields
    )
}

@pbandk.Export
@pbandk.JsName("orDefaultForWrpGuestMessageReqStartMetadataEntry")
fun WrpGuestMessageReqStart.MetadataEntry?.orDefault(): WrpGuestMessageReqStart.MetadataEntry =
    this ?: WrpGuestMessageReqStart.MetadataEntry.defaultInstance

private fun WrpGuestMessageReqStart.MetadataEntry.protoMergeImpl(plus: pbandk.Message?): WrpGuestMessageReqStart.MetadataEntry =
    (plus as? WrpGuestMessageReqStart.MetadataEntry)?.copy(
        unknownFields = unknownFields + plus.unknownFields
    ) ?: this

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
fun WrpGuestMessageReqPayload?.orDefault(): WrpGuestMessageReqPayload =
    this ?: WrpGuestMessageReqPayload.defaultInstance

private fun WrpGuestMessageReqPayload.protoMergeImpl(plus: pbandk.Message?): WrpGuestMessageReqPayload =
    (plus as? WrpGuestMessageReqPayload)?.copy(
        unknownFields = unknownFields + plus.unknownFields
    ) ?: this

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
fun WrpGuestMessageReqFinish?.orDefault(): WrpGuestMessageReqFinish =
    this ?: WrpGuestMessageReqFinish.defaultInstance

private fun WrpGuestMessageReqFinish.protoMergeImpl(plus: pbandk.Message?): WrpGuestMessageReqFinish =
    (plus as? WrpGuestMessageReqFinish)?.copy(
        unknownFields = unknownFields + plus.unknownFields
    ) ?: this

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
fun WrpGuestMessageResFinish?.orDefault(): WrpGuestMessageResFinish =
    this ?: WrpGuestMessageResFinish.defaultInstance

private fun WrpGuestMessageResFinish.protoMergeImpl(plus: pbandk.Message?): WrpGuestMessageResFinish =
    (plus as? WrpGuestMessageResFinish)?.copy(
        unknownFields = unknownFields + plus.unknownFields
    ) ?: this

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
