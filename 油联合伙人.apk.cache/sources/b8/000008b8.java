package com.alibaba.sdk.android.oss.internal;

import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.common.OSSHeaders;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.utils.DateUtil;
import com.alibaba.sdk.android.oss.common.utils.HttpHeaders;
import com.alibaba.sdk.android.oss.model.AbortMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.AppendObjectResult;
import com.alibaba.sdk.android.oss.model.CompleteMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.CopyObjectResult;
import com.alibaba.sdk.android.oss.model.CreateBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteBucketResult;
import com.alibaba.sdk.android.oss.model.DeleteObjectResult;
import com.alibaba.sdk.android.oss.model.GetBucketACLResult;
import com.alibaba.sdk.android.oss.model.GetObjectResult;
import com.alibaba.sdk.android.oss.model.HeadObjectResult;
import com.alibaba.sdk.android.oss.model.InitiateMultipartUploadResult;
import com.alibaba.sdk.android.oss.model.ListObjectsResult;
import com.alibaba.sdk.android.oss.model.ListPartsResult;
import com.alibaba.sdk.android.oss.model.OSSObjectSummary;
import com.alibaba.sdk.android.oss.model.ObjectMetadata;
import com.alibaba.sdk.android.oss.model.PartSummary;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.alibaba.sdk.android.oss.model.UploadPartResult;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import okhttp3.Headers;
import okhttp3.Response;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/* loaded from: classes.dex */
public final class ResponseParsers {
    public static final DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();

    /* loaded from: classes.dex */
    public static final class PutObjectReponseParser implements ResponseParser<PutObjectResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
        public PutObjectResult parse(Response response) throws IOException {
            try {
                PutObjectResult putObjectResult = new PutObjectResult();
                putObjectResult.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                putObjectResult.setStatusCode(response.code());
                putObjectResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                putObjectResult.setETag(ResponseParsers.trimQuotes(response.header(HttpHeaders.ETAG)));
                if (response.body().contentLength() > 0) {
                    putObjectResult.setServerCallbackReturnBody(response.body().string());
                }
                return putObjectResult;
            } finally {
                ResponseParsers.safeCloseResponse(response);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class AppendObjectResponseParser implements ResponseParser<AppendObjectResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
        public AppendObjectResult parse(Response response) throws IOException {
            try {
                AppendObjectResult appendObjectResult = new AppendObjectResult();
                appendObjectResult.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                appendObjectResult.setStatusCode(response.code());
                appendObjectResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                String header = response.header(OSSHeaders.OSS_NEXT_APPEND_POSITION);
                if (header != null) {
                    appendObjectResult.setNextPosition(Long.valueOf(header));
                }
                appendObjectResult.setObjectCRC64(response.header(OSSHeaders.OSS_HASH_CRC64_ECMA));
                return appendObjectResult;
            } finally {
                ResponseParsers.safeCloseResponse(response);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class HeadObjectResponseParser implements ResponseParser<HeadObjectResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
        public HeadObjectResult parse(Response response) throws IOException {
            HeadObjectResult headObjectResult = new HeadObjectResult();
            try {
                headObjectResult.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                headObjectResult.setStatusCode(response.code());
                headObjectResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                headObjectResult.setMetadata(ResponseParsers.parseObjectMetadata(headObjectResult.getResponseHeader()));
                return headObjectResult;
            } finally {
                ResponseParsers.safeCloseResponse(response);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class GetObjectResponseParser implements ResponseParser<GetObjectResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
        public GetObjectResult parse(Response response) throws IOException {
            GetObjectResult getObjectResult = new GetObjectResult();
            getObjectResult.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
            getObjectResult.setStatusCode(response.code());
            getObjectResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
            getObjectResult.setMetadata(ResponseParsers.parseObjectMetadata(getObjectResult.getResponseHeader()));
            getObjectResult.setContentLength(response.body().contentLength());
            getObjectResult.setObjectContent(response.body().byteStream());
            return getObjectResult;
        }
    }

    /* loaded from: classes.dex */
    public static final class CopyObjectResponseParser implements ResponseParser<CopyObjectResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
        public CopyObjectResult parse(Response response) throws IOException {
            try {
                try {
                    CopyObjectResult parseCopyObjectResponseXML = ResponseParsers.parseCopyObjectResponseXML(response.body().byteStream());
                    parseCopyObjectResponseXML.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                    parseCopyObjectResponseXML.setStatusCode(response.code());
                    parseCopyObjectResponseXML.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                    return parseCopyObjectResponseXML;
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new IOException(e.getMessage(), e);
                }
            } finally {
                ResponseParsers.safeCloseResponse(response);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class CreateBucketResponseParser implements ResponseParser<CreateBucketResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
        public CreateBucketResult parse(Response response) throws IOException {
            try {
                try {
                    CreateBucketResult createBucketResult = new CreateBucketResult();
                    createBucketResult.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                    createBucketResult.setStatusCode(response.code());
                    createBucketResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                    return createBucketResult;
                } catch (Exception e) {
                    throw new IOException(e.getMessage(), e);
                }
            } finally {
                ResponseParsers.safeCloseResponse(response);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class DeleteBucketResponseParser implements ResponseParser<DeleteBucketResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
        public DeleteBucketResult parse(Response response) throws IOException {
            try {
                DeleteBucketResult deleteBucketResult = new DeleteBucketResult();
                deleteBucketResult.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                deleteBucketResult.setStatusCode(response.code());
                deleteBucketResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                return deleteBucketResult;
            } finally {
                ResponseParsers.safeCloseResponse(response);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class GetBucketACLResponseParser implements ResponseParser<GetBucketACLResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
        public GetBucketACLResult parse(Response response) throws IOException {
            try {
                try {
                    GetBucketACLResult parseGetBucketACLResponse = ResponseParsers.parseGetBucketACLResponse(response.body().byteStream());
                    parseGetBucketACLResponse.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                    parseGetBucketACLResponse.setStatusCode(response.code());
                    parseGetBucketACLResponse.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                    return parseGetBucketACLResponse;
                } catch (Exception e) {
                    throw new IOException(e.getMessage(), e);
                }
            } finally {
                ResponseParsers.safeCloseResponse(response);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class DeleteObjectResponseParser implements ResponseParser<DeleteObjectResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
        public DeleteObjectResult parse(Response response) throws IOException {
            DeleteObjectResult deleteObjectResult = new DeleteObjectResult();
            try {
                deleteObjectResult.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                deleteObjectResult.setStatusCode(response.code());
                deleteObjectResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                return deleteObjectResult;
            } finally {
                ResponseParsers.safeCloseResponse(response);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class ListObjectsResponseParser implements ResponseParser<ListObjectsResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
        public ListObjectsResult parse(Response response) throws IOException {
            try {
                try {
                    ListObjectsResult parseObjectListResponse = ResponseParsers.parseObjectListResponse(response.body().byteStream());
                    parseObjectListResponse.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                    parseObjectListResponse.setStatusCode(response.code());
                    parseObjectListResponse.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                    return parseObjectListResponse;
                } catch (Exception e) {
                    throw new IOException(e.getMessage(), e);
                }
            } finally {
                ResponseParsers.safeCloseResponse(response);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class InitMultipartResponseParser implements ResponseParser<InitiateMultipartUploadResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
        public InitiateMultipartUploadResult parse(Response response) throws IOException {
            try {
                try {
                    InitiateMultipartUploadResult parseInitMultipartResponseXML = ResponseParsers.parseInitMultipartResponseXML(response.body().byteStream());
                    parseInitMultipartResponseXML.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                    parseInitMultipartResponseXML.setStatusCode(response.code());
                    parseInitMultipartResponseXML.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                    return parseInitMultipartResponseXML;
                } catch (Exception e) {
                    throw new IOException(e.getMessage(), e);
                }
            } finally {
                ResponseParsers.safeCloseResponse(response);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class UploadPartResponseParser implements ResponseParser<UploadPartResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
        public UploadPartResult parse(Response response) throws IOException {
            try {
                UploadPartResult uploadPartResult = new UploadPartResult();
                uploadPartResult.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                uploadPartResult.setStatusCode(response.code());
                uploadPartResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                uploadPartResult.setETag(ResponseParsers.trimQuotes(response.header(HttpHeaders.ETAG)));
                return uploadPartResult;
            } finally {
                ResponseParsers.safeCloseResponse(response);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class AbortMultipartUploadResponseParser implements ResponseParser<AbortMultipartUploadResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
        public AbortMultipartUploadResult parse(Response response) throws IOException {
            try {
                AbortMultipartUploadResult abortMultipartUploadResult = new AbortMultipartUploadResult();
                abortMultipartUploadResult.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                abortMultipartUploadResult.setStatusCode(response.code());
                abortMultipartUploadResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                return abortMultipartUploadResult;
            } finally {
                ResponseParsers.safeCloseResponse(response);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class CompleteMultipartUploadResponseParser implements ResponseParser<CompleteMultipartUploadResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
        public CompleteMultipartUploadResult parse(Response response) throws IOException {
            try {
                try {
                    CompleteMultipartUploadResult completeMultipartUploadResult = new CompleteMultipartUploadResult();
                    if (response.header("Content-Type").equals("application/xml")) {
                        completeMultipartUploadResult = ResponseParsers.parseCompleteMultipartUploadResponseXML(response.body().byteStream());
                    } else if (response.body() != null) {
                        completeMultipartUploadResult.setServerCallbackReturnBody(response.body().string());
                    }
                    completeMultipartUploadResult.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                    completeMultipartUploadResult.setStatusCode(response.code());
                    completeMultipartUploadResult.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                    return completeMultipartUploadResult;
                } catch (Exception e) {
                    throw new IOException(e.getMessage(), e);
                }
            } finally {
                ResponseParsers.safeCloseResponse(response);
            }
        }
    }

    /* loaded from: classes.dex */
    public static final class ListPartsResponseParser implements ResponseParser<ListPartsResult> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.alibaba.sdk.android.oss.internal.ResponseParser
        public ListPartsResult parse(Response response) throws IOException {
            try {
                try {
                    ListPartsResult parseListPartsResponseXML = ResponseParsers.parseListPartsResponseXML(response.body().byteStream());
                    parseListPartsResponseXML.setRequestId(response.header(OSSHeaders.OSS_HEADER_REQUEST_ID));
                    parseListPartsResponseXML.setStatusCode(response.code());
                    parseListPartsResponseXML.setResponseHeader(ResponseParsers.parseResponseHeader(response));
                    return parseListPartsResponseXML;
                } catch (Exception e) {
                    throw new IOException(e.getMessage(), e);
                }
            } finally {
                ResponseParsers.safeCloseResponse(response);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CopyObjectResult parseCopyObjectResponseXML(InputStream inputStream) throws ParseException, ParserConfigurationException, IOException, SAXException {
        CopyObjectResult copyObjectResult = new CopyObjectResult();
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logD("[item] - " + documentElement.getNodeName());
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null) {
                if (nodeName.equals("LastModified")) {
                    copyObjectResult.setLastModified(DateUtil.parseIso8601Date(checkChildNotNullAndGetValue(item)));
                } else if (nodeName.equals(HttpHeaders.ETAG)) {
                    copyObjectResult.setEtag(checkChildNotNullAndGetValue(item));
                }
            }
        }
        return copyObjectResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ListPartsResult parseListPartsResponseXML(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException, ParseException {
        String checkChildNotNullAndGetValue;
        ListPartsResult listPartsResult = new ListPartsResult();
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logD("[parseObjectListResponse] - " + documentElement.getNodeName());
        ArrayList arrayList = new ArrayList();
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null) {
                if (nodeName.equals("Bucket")) {
                    listPartsResult.setBucketName(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("Key")) {
                    listPartsResult.setKey(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("UploadId")) {
                    listPartsResult.setUploadId(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("PartNumberMarker")) {
                    String checkChildNotNullAndGetValue2 = checkChildNotNullAndGetValue(item);
                    if (checkChildNotNullAndGetValue2 != null) {
                        listPartsResult.setPartNumberMarker(Integer.valueOf(checkChildNotNullAndGetValue2).intValue());
                    }
                } else if (nodeName.equals("NextPartNumberMarker")) {
                    String checkChildNotNullAndGetValue3 = checkChildNotNullAndGetValue(item);
                    if (checkChildNotNullAndGetValue3 != null) {
                        listPartsResult.setNextPartNumberMarker(Integer.valueOf(checkChildNotNullAndGetValue3).intValue());
                    }
                } else if (nodeName.equals("MaxParts")) {
                    String checkChildNotNullAndGetValue4 = checkChildNotNullAndGetValue(item);
                    if (checkChildNotNullAndGetValue4 != null) {
                        listPartsResult.setMaxParts(Integer.valueOf(checkChildNotNullAndGetValue4).intValue());
                    }
                } else if (nodeName.equals("IsTruncated")) {
                    String checkChildNotNullAndGetValue5 = checkChildNotNullAndGetValue(item);
                    if (checkChildNotNullAndGetValue5 != null) {
                        listPartsResult.setTruncated(Boolean.valueOf(checkChildNotNullAndGetValue5).booleanValue());
                    }
                } else if (nodeName.equals("Part")) {
                    NodeList childNodes2 = item.getChildNodes();
                    PartSummary partSummary = new PartSummary();
                    for (int i2 = 0; i2 < childNodes2.getLength(); i2++) {
                        Node item2 = childNodes2.item(i2);
                        String nodeName2 = item2.getNodeName();
                        if (nodeName2 != null) {
                            if (nodeName2.equals("PartNumber")) {
                                String checkChildNotNullAndGetValue6 = checkChildNotNullAndGetValue(item2);
                                if (checkChildNotNullAndGetValue6 != null) {
                                    partSummary.setPartNumber(Integer.valueOf(checkChildNotNullAndGetValue6).intValue());
                                }
                            } else if (nodeName2.equals("LastModified")) {
                                partSummary.setLastModified(DateUtil.parseIso8601Date(checkChildNotNullAndGetValue(item2)));
                            } else if (nodeName2.equals(HttpHeaders.ETAG)) {
                                partSummary.setETag(checkChildNotNullAndGetValue(item2));
                            } else if (nodeName2.equals("Size") && (checkChildNotNullAndGetValue = checkChildNotNullAndGetValue(item2)) != null) {
                                partSummary.setSize(Integer.valueOf(checkChildNotNullAndGetValue).intValue());
                            }
                        }
                    }
                    arrayList.add(partSummary);
                }
            }
        }
        listPartsResult.setParts(arrayList);
        return listPartsResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static CompleteMultipartUploadResult parseCompleteMultipartUploadResponseXML(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException {
        CompleteMultipartUploadResult completeMultipartUploadResult = new CompleteMultipartUploadResult();
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logD("[item] - " + documentElement.getNodeName());
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null) {
                if (nodeName.equalsIgnoreCase(HttpHeaders.LOCATION)) {
                    completeMultipartUploadResult.setLocation(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equalsIgnoreCase("Bucket")) {
                    completeMultipartUploadResult.setBucketName(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equalsIgnoreCase("Key")) {
                    completeMultipartUploadResult.setObjectKey(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equalsIgnoreCase(HttpHeaders.ETAG)) {
                    completeMultipartUploadResult.setETag(checkChildNotNullAndGetValue(item));
                }
            }
        }
        return completeMultipartUploadResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static InitiateMultipartUploadResult parseInitMultipartResponseXML(InputStream inputStream) throws IOException, SAXException, ParserConfigurationException {
        InitiateMultipartUploadResult initiateMultipartUploadResult = new InitiateMultipartUploadResult();
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logD("[item] - " + documentElement.getNodeName());
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null) {
                if (nodeName.equalsIgnoreCase("UploadId")) {
                    initiateMultipartUploadResult.setUploadId(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equalsIgnoreCase("Bucket")) {
                    initiateMultipartUploadResult.setBucketName(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equalsIgnoreCase("Key")) {
                    initiateMultipartUploadResult.setObjectKey(checkChildNotNullAndGetValue(item));
                }
            }
        }
        return initiateMultipartUploadResult;
    }

    private static OSSObjectSummary parseObjectSummaryXML(NodeList nodeList) throws ParseException {
        OSSObjectSummary oSSObjectSummary = new OSSObjectSummary();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null) {
                if (nodeName.equals("Key")) {
                    oSSObjectSummary.setKey(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("LastModified")) {
                    oSSObjectSummary.setLastModified(DateUtil.parseIso8601Date(checkChildNotNullAndGetValue(item)));
                } else if (nodeName.equals("Size")) {
                    String checkChildNotNullAndGetValue = checkChildNotNullAndGetValue(item);
                    if (checkChildNotNullAndGetValue != null) {
                        oSSObjectSummary.setSize(Integer.valueOf(checkChildNotNullAndGetValue).intValue());
                    }
                } else if (nodeName.equals(HttpHeaders.ETAG)) {
                    oSSObjectSummary.setETag(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("Type")) {
                    oSSObjectSummary.setType(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("StorageClass")) {
                    oSSObjectSummary.setStorageClass(checkChildNotNullAndGetValue(item));
                }
            }
        }
        return oSSObjectSummary;
    }

    private static String parseCommonPrefixXML(NodeList nodeList) {
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node item = nodeList.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null && nodeName.equals("Prefix")) {
                return checkChildNotNullAndGetValue(item);
            }
        }
        return "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static GetBucketACLResult parseGetBucketACLResponse(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException, ParseException {
        GetBucketACLResult getBucketACLResult = new GetBucketACLResult();
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logD("[parseGetBucketACLResponse - " + documentElement.getNodeName());
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null) {
                if (nodeName.equals("Owner")) {
                    NodeList childNodes2 = item.getChildNodes();
                    for (int i2 = 0; i2 < childNodes2.getLength(); i2++) {
                        Node item2 = childNodes2.item(i2);
                        String nodeName2 = item2.getNodeName();
                        if (nodeName2 != null) {
                            if (nodeName2.equals("ID")) {
                                getBucketACLResult.setBucketOwnerID(checkChildNotNullAndGetValue(item2));
                            } else if (nodeName2.equals("DisplayName")) {
                                getBucketACLResult.setBucketOwner(checkChildNotNullAndGetValue(item2));
                            }
                        }
                    }
                } else if (nodeName.equals("AccessControlList")) {
                    NodeList childNodes3 = item.getChildNodes();
                    for (int i3 = 0; i3 < childNodes3.getLength(); i3++) {
                        Node item3 = childNodes3.item(i3);
                        String nodeName3 = item3.getNodeName();
                        if (nodeName3 != null && nodeName3.equals("Grant")) {
                            getBucketACLResult.setBucketACL(checkChildNotNullAndGetValue(item3));
                        }
                    }
                }
            }
        }
        return getBucketACLResult;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static ListObjectsResult parseObjectListResponse(InputStream inputStream) throws ParserConfigurationException, IOException, SAXException, ParseException {
        String parseCommonPrefixXML;
        ListObjectsResult listObjectsResult = new ListObjectsResult();
        Element documentElement = domFactory.newDocumentBuilder().parse(inputStream).getDocumentElement();
        OSSLog.logD("[parseObjectListResponse] - " + documentElement.getNodeName());
        NodeList childNodes = documentElement.getChildNodes();
        for (int i = 0; i < childNodes.getLength(); i++) {
            Node item = childNodes.item(i);
            String nodeName = item.getNodeName();
            if (nodeName != null) {
                if (nodeName.equals("Name")) {
                    listObjectsResult.setBucketName(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("Prefix")) {
                    listObjectsResult.setPrefix(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("Marker")) {
                    listObjectsResult.setMarker(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("Delimiter")) {
                    listObjectsResult.setDelimiter(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("EncodingType")) {
                    listObjectsResult.setEncodingType(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("MaxKeys")) {
                    String checkChildNotNullAndGetValue = checkChildNotNullAndGetValue(item);
                    if (checkChildNotNullAndGetValue != null) {
                        listObjectsResult.setMaxKeys(Integer.valueOf(checkChildNotNullAndGetValue).intValue());
                    }
                } else if (nodeName.equals("NextMarker")) {
                    listObjectsResult.setNextMarker(checkChildNotNullAndGetValue(item));
                } else if (nodeName.equals("IsTruncated")) {
                    String checkChildNotNullAndGetValue2 = checkChildNotNullAndGetValue(item);
                    if (checkChildNotNullAndGetValue2 != null) {
                        listObjectsResult.setTruncated(Boolean.valueOf(checkChildNotNullAndGetValue2).booleanValue());
                    }
                } else if (nodeName.equals("Contents")) {
                    if (item.getChildNodes() != null) {
                        listObjectsResult.getObjectSummaries().add(parseObjectSummaryXML(item.getChildNodes()));
                    }
                } else if (nodeName.equals("CommonPrefixes") && item.getChildNodes() != null && (parseCommonPrefixXML = parseCommonPrefixXML(item.getChildNodes())) != null) {
                    listObjectsResult.getCommonPrefixes().add(parseCommonPrefixXML);
                }
            }
        }
        return listObjectsResult;
    }

    public static String trimQuotes(String str) {
        if (str == null) {
            return null;
        }
        String trim = str.trim();
        if (trim.startsWith("\"")) {
            trim = trim.substring(1);
        }
        return trim.endsWith("\"") ? trim.substring(0, trim.length() - 1) : trim;
    }

    public static ObjectMetadata parseObjectMetadata(Map<String, String> map) throws IOException {
        try {
            ObjectMetadata objectMetadata = new ObjectMetadata();
            for (String str : map.keySet()) {
                if (str.indexOf(OSSHeaders.OSS_USER_METADATA_PREFIX) >= 0) {
                    objectMetadata.addUserMetadata(str, map.get(str));
                } else {
                    if (!str.equals(HttpHeaders.LAST_MODIFIED) && !str.equals(HttpHeaders.DATE)) {
                        if (str.equals("Content-Length")) {
                            objectMetadata.setHeader(str, Long.valueOf(map.get(str)));
                        } else if (str.equals(HttpHeaders.ETAG)) {
                            objectMetadata.setHeader(str, trimQuotes(map.get(str)));
                        } else {
                            objectMetadata.setHeader(str, map.get(str));
                        }
                    }
                    try {
                        objectMetadata.setHeader(str, DateUtil.parseRfc822Date(map.get(str)));
                    } catch (ParseException e) {
                        throw new IOException(e.getMessage(), e);
                    }
                }
            }
            return objectMetadata;
        } catch (Exception e2) {
            throw new IOException(e2.getMessage(), e2);
        }
    }

    public static Map<String, String> parseResponseHeader(Response response) {
        HashMap hashMap = new HashMap();
        Headers headers = response.headers();
        for (int i = 0; i < headers.size(); i++) {
            hashMap.put(headers.name(i), headers.value(i));
        }
        return hashMap;
    }

    public static ServiceException parseResponseErrorXML(Response response, boolean z) throws IOException {
        String str;
        String str2;
        String str3;
        String str4;
        String str5;
        String str6;
        String str7;
        int code = response.code();
        String header = response.header(OSSHeaders.OSS_HEADER_REQUEST_ID);
        String str8 = null;
        if (z) {
            str4 = header;
            str5 = null;
            str6 = null;
            str7 = null;
        } else {
            try {
                str = response.body().string();
                try {
                    NodeList childNodes = domFactory.newDocumentBuilder().parse(new InputSource(new StringReader(str))).getDocumentElement().getChildNodes();
                    str2 = null;
                    str3 = null;
                    for (int i = 0; i < childNodes.getLength(); i++) {
                        try {
                            Node item = childNodes.item(i);
                            String nodeName = item.getNodeName();
                            if (nodeName != null) {
                                if (nodeName.equals("Code")) {
                                    str8 = checkChildNotNullAndGetValue(item);
                                }
                                if (nodeName.equals("Message")) {
                                    str2 = checkChildNotNullAndGetValue(item);
                                }
                                if (nodeName.equals("RequestId")) {
                                    header = checkChildNotNullAndGetValue(item);
                                }
                                if (nodeName.equals("HostId")) {
                                    str3 = checkChildNotNullAndGetValue(item);
                                }
                            }
                        } catch (ParserConfigurationException e) {
                            e = e;
                            e.printStackTrace();
                            str4 = header;
                            str5 = str8;
                            str8 = str2;
                            str6 = str3;
                            str7 = str;
                            return new ServiceException(code, str8, str5, str4, str6, str7);
                        } catch (SAXException e2) {
                            e = e2;
                            e.printStackTrace();
                            str4 = header;
                            str5 = str8;
                            str8 = str2;
                            str6 = str3;
                            str7 = str;
                            return new ServiceException(code, str8, str5, str4, str6, str7);
                        }
                    }
                    response.body().close();
                } catch (ParserConfigurationException e3) {
                    e = e3;
                    str2 = null;
                    str3 = str2;
                    e.printStackTrace();
                    str4 = header;
                    str5 = str8;
                    str8 = str2;
                    str6 = str3;
                    str7 = str;
                    return new ServiceException(code, str8, str5, str4, str6, str7);
                } catch (SAXException e4) {
                    e = e4;
                    str2 = null;
                    str3 = str2;
                    e.printStackTrace();
                    str4 = header;
                    str5 = str8;
                    str8 = str2;
                    str6 = str3;
                    str7 = str;
                    return new ServiceException(code, str8, str5, str4, str6, str7);
                }
            } catch (ParserConfigurationException e5) {
                e = e5;
                str = null;
                str2 = null;
            } catch (SAXException e6) {
                e = e6;
                str = null;
                str2 = null;
            }
            str4 = header;
            str5 = str8;
            str8 = str2;
            str6 = str3;
            str7 = str;
        }
        return new ServiceException(code, str8, str5, str4, str6, str7);
    }

    public static String checkChildNotNullAndGetValue(Node node) {
        if (node.getFirstChild() != null) {
            return node.getFirstChild().getNodeValue();
        }
        return null;
    }

    public static void safeCloseResponse(Response response) {
        try {
            response.body().close();
        } catch (Exception unused) {
        }
    }
}