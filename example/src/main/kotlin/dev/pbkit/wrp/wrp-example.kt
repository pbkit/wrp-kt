@file:OptIn(pbandk.PublicForGeneratedCode::class)

package dev.pbkit.wrp

@pbandk.Export
public data class GetTextValueRequest(
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.GetTextValueRequest = protoMergeImpl(other)
    override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.GetTextValueRequest> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }
    public companion object : pbandk.Message.Companion<dev.pbkit.wrp.GetTextValueRequest> {
        public val defaultInstance: dev.pbkit.wrp.GetTextValueRequest by lazy { dev.pbkit.wrp.GetTextValueRequest() }
        override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.GetTextValueRequest = dev.pbkit.wrp.GetTextValueRequest.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.GetTextValueRequest> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.GetTextValueRequest, *>>(0)
            fieldsList.apply {
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.example.GetTextValueRequest",
                messageClass = dev.pbkit.wrp.GetTextValueRequest::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
public data class GetTextValueResponse(
    val text: String = "",
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.GetTextValueResponse = protoMergeImpl(other)
    override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.GetTextValueResponse> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }
    public companion object : pbandk.Message.Companion<dev.pbkit.wrp.GetTextValueResponse> {
        public val defaultInstance: dev.pbkit.wrp.GetTextValueResponse by lazy { dev.pbkit.wrp.GetTextValueResponse() }
        override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.GetTextValueResponse = dev.pbkit.wrp.GetTextValueResponse.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.GetTextValueResponse> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.GetTextValueResponse, *>>(1)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "text",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.String(),
                        jsonName = "text",
                        value = dev.pbkit.wrp.GetTextValueResponse::text
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.example.GetTextValueResponse",
                messageClass = dev.pbkit.wrp.GetTextValueResponse::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
public data class GetSliderValueRequest(
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.GetSliderValueRequest = protoMergeImpl(other)
    override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.GetSliderValueRequest> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }
    public companion object : pbandk.Message.Companion<dev.pbkit.wrp.GetSliderValueRequest> {
        public val defaultInstance: dev.pbkit.wrp.GetSliderValueRequest by lazy { dev.pbkit.wrp.GetSliderValueRequest() }
        override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.GetSliderValueRequest = dev.pbkit.wrp.GetSliderValueRequest.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.GetSliderValueRequest> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.GetSliderValueRequest, *>>(0)
            fieldsList.apply {
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.example.GetSliderValueRequest",
                messageClass = dev.pbkit.wrp.GetSliderValueRequest::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
public data class GetSliderValueResponse(
    val value: Int = 0,
    override val unknownFields: Map<Int, pbandk.UnknownField> = emptyMap()
) : pbandk.Message {
    override operator fun plus(other: pbandk.Message?): dev.pbkit.wrp.GetSliderValueResponse = protoMergeImpl(other)
    override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.GetSliderValueResponse> get() = Companion.descriptor
    override val protoSize: Int by lazy { super.protoSize }
    public companion object : pbandk.Message.Companion<dev.pbkit.wrp.GetSliderValueResponse> {
        public val defaultInstance: dev.pbkit.wrp.GetSliderValueResponse by lazy { dev.pbkit.wrp.GetSliderValueResponse() }
        override fun decodeWith(u: pbandk.MessageDecoder): dev.pbkit.wrp.GetSliderValueResponse = dev.pbkit.wrp.GetSliderValueResponse.decodeWithImpl(u)

        override val descriptor: pbandk.MessageDescriptor<dev.pbkit.wrp.GetSliderValueResponse> by lazy {
            val fieldsList = ArrayList<pbandk.FieldDescriptor<dev.pbkit.wrp.GetSliderValueResponse, *>>(1)
            fieldsList.apply {
                add(
                    pbandk.FieldDescriptor(
                        messageDescriptor = this@Companion::descriptor,
                        name = "value",
                        number = 1,
                        type = pbandk.FieldDescriptor.Type.Primitive.Int32(),
                        jsonName = "value",
                        value = dev.pbkit.wrp.GetSliderValueResponse::value
                    )
                )
            }
            pbandk.MessageDescriptor(
                fullName = "pbkit.wrp.example.GetSliderValueResponse",
                messageClass = dev.pbkit.wrp.GetSliderValueResponse::class,
                messageCompanion = this,
                fields = fieldsList
            )
        }
    }
}

@pbandk.Export
@pbandk.JsName("orDefaultForGetTextValueRequest")
public fun GetTextValueRequest?.orDefault(): dev.pbkit.wrp.GetTextValueRequest = this ?: GetTextValueRequest.defaultInstance

private fun GetTextValueRequest.protoMergeImpl(plus: pbandk.Message?): GetTextValueRequest = (plus as? GetTextValueRequest)?.let {
    it.copy(
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun GetTextValueRequest.Companion.decodeWithImpl(u: pbandk.MessageDecoder): GetTextValueRequest {

    val unknownFields = u.readMessage(this) { _, _ -> }
    return GetTextValueRequest(unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForGetTextValueResponse")
public fun GetTextValueResponse?.orDefault(): dev.pbkit.wrp.GetTextValueResponse = this ?: GetTextValueResponse.defaultInstance

private fun GetTextValueResponse.protoMergeImpl(plus: pbandk.Message?): GetTextValueResponse = (plus as? GetTextValueResponse)?.let {
    it.copy(
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun GetTextValueResponse.Companion.decodeWithImpl(u: pbandk.MessageDecoder): GetTextValueResponse {
    var text = ""

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> text = _fieldValue as String
        }
    }
    return GetTextValueResponse(text, unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForGetSliderValueRequest")
public fun GetSliderValueRequest?.orDefault(): dev.pbkit.wrp.GetSliderValueRequest = this ?: GetSliderValueRequest.defaultInstance

private fun GetSliderValueRequest.protoMergeImpl(plus: pbandk.Message?): GetSliderValueRequest = (plus as? GetSliderValueRequest)?.let {
    it.copy(
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun GetSliderValueRequest.Companion.decodeWithImpl(u: pbandk.MessageDecoder): GetSliderValueRequest {

    val unknownFields = u.readMessage(this) { _, _ -> }
    return GetSliderValueRequest(unknownFields)
}

@pbandk.Export
@pbandk.JsName("orDefaultForGetSliderValueResponse")
public fun GetSliderValueResponse?.orDefault(): dev.pbkit.wrp.GetSliderValueResponse = this ?: GetSliderValueResponse.defaultInstance

private fun GetSliderValueResponse.protoMergeImpl(plus: pbandk.Message?): GetSliderValueResponse = (plus as? GetSliderValueResponse)?.let {
    it.copy(
        unknownFields = unknownFields + plus.unknownFields
    )
} ?: this

@Suppress("UNCHECKED_CAST")
private fun GetSliderValueResponse.Companion.decodeWithImpl(u: pbandk.MessageDecoder): GetSliderValueResponse {
    var value = 0

    val unknownFields = u.readMessage(this) { _fieldNumber, _fieldValue ->
        when (_fieldNumber) {
            1 -> value = _fieldValue as Int
        }
    }
    return GetSliderValueResponse(value, unknownFields)
}
