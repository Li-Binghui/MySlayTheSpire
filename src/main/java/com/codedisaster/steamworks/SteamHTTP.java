/*     */ package com.codedisaster.steamworks;
/*     */ 
/*     */ import java.nio.ByteBuffer;
/*     */ 
/*     */ public class SteamHTTP extends SteamInterface {
/*     */   private final boolean isServer;
/*     */   
/*     */   public enum HTTPMethod {
/*   9 */     Invalid,
/*  10 */     GET,
/*  11 */     HEAD,
/*  12 */     POST,
/*  13 */     PUT,
/*  14 */     DELETE,
/*  15 */     OPTIONS;
/*     */   }
/*     */   
/*     */   public enum HTTPStatusCode {
/*  19 */     Invalid(0),
/*     */     
/*  21 */     Continue(100),
/*  22 */     SwitchingProtocols(101),
/*     */     
/*  24 */     OK(200),
/*  25 */     Created(201),
/*  26 */     Accepted(202),
/*  27 */     NonAuthoritative(203),
/*  28 */     NoContent(204),
/*  29 */     ResetContent(205),
/*  30 */     PartialContent(206),
/*     */     
/*  32 */     MultipleChoices(300),
/*  33 */     MovedPermanently(301),
/*  34 */     Found(302),
/*  35 */     SeeOther(303),
/*  36 */     NotModified(304),
/*  37 */     UseProxy(305),
/*  38 */     TemporaryRedirect(307),
/*     */     
/*  40 */     BadRequest(400),
/*  41 */     Unauthorized(401),
/*  42 */     PaymentRequired(402),
/*  43 */     Forbidden(403),
/*  44 */     NotFound(404),
/*  45 */     MethodNotAllowed(405),
/*  46 */     NotAcceptable(406),
/*  47 */     ProxyAuthRequired(407),
/*  48 */     RequestTimeout(408),
/*  49 */     Conflict(409),
/*  50 */     Gone(410),
/*  51 */     LengthRequired(411),
/*  52 */     PreconditionFailed(412),
/*  53 */     RequestEntityTooLarge(413),
/*  54 */     RequestURITooLong(414),
/*  55 */     UnsupportedMediaType(415),
/*  56 */     RequestedRangeNotSatisfiable(416),
/*  57 */     ExpectationFailed(417),
/*  58 */     Unknown4xx(418),
/*  59 */     TooManyRequests(429),
/*     */     
/*  61 */     InternalServerError(500),
/*  62 */     NotImplemented(501),
/*  63 */     BadGateway(502),
/*  64 */     ServiceUnavailable(503),
/*  65 */     GatewayTimeout(504),
/*  66 */     HTTPVersionNotSupported(505),
/*  67 */     Unknown5xx(599);
/*     */     
/*     */     private final int code;
/*  70 */     private static final HTTPStatusCode[] values = values();
/*     */     
/*     */     HTTPStatusCode(int code) {
/*  73 */       this.code = code;
/*     */     }
/*     */ 
/*     */     
/*     */     static {
/*     */     
/*     */     }
/*     */     
/*     */     static HTTPStatusCode byValue(int statusCode) {
/*  82 */       int from = 0;
/*  83 */       int to = values.length - 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  89 */       while (from <= to) {
/*  90 */         int idx = (from + to) / 2;
/*  91 */         HTTPStatusCode value = values[idx];
/*  92 */         if (statusCode < value.code) {
/*  93 */           to = idx - 1; continue;
/*  94 */         }  if (statusCode > value.code) {
/*  95 */           from = idx + 1; continue;
/*     */         } 
/*  97 */         return value;
/*     */       } 
/*     */ 
/*     */       
/* 101 */       return Invalid;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public SteamHTTP(SteamHTTPCallback callback) {
/* 108 */     this(false, SteamHTTPNative.createCallback(new SteamHTTPCallbackAdapter(callback)));
/*     */   }
/*     */   
/*     */   SteamHTTP(boolean isServer, long callback) {
/* 112 */     super(callback);
/* 113 */     this.isServer = isServer;
/*     */   }
/*     */   
/*     */   public SteamHTTPRequestHandle createHTTPRequest(HTTPMethod requestMethod, String absoluteURL) {
/* 117 */     return new SteamHTTPRequestHandle(SteamHTTPNative.createHTTPRequest(this.isServer, requestMethod.ordinal(), absoluteURL));
/*     */   }
/*     */   
/*     */   public boolean setHTTPRequestContextValue(SteamHTTPRequestHandle request, long contextValue) {
/* 121 */     return SteamHTTPNative.setHTTPRequestContextValue(this.isServer, request.handle, contextValue);
/*     */   }
/*     */   
/*     */   public boolean setHTTPRequestNetworkActivityTimeout(SteamHTTPRequestHandle request, int timeoutSeconds) {
/* 125 */     return SteamHTTPNative.setHTTPRequestNetworkActivityTimeout(this.isServer, request.handle, timeoutSeconds);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setHTTPRequestHeaderValue(SteamHTTPRequestHandle request, String headerName, String headerValue) {
/* 131 */     return SteamHTTPNative.setHTTPRequestHeaderValue(this.isServer, request.handle, headerName, headerValue);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setHTTPRequestGetOrPostParameter(SteamHTTPRequestHandle request, String paramName, String paramValue) {
/* 137 */     return SteamHTTPNative.setHTTPRequestGetOrPostParameter(this.isServer, request.handle, paramName, paramValue);
/*     */   }
/*     */   
/*     */   public SteamAPICall sendHTTPRequest(SteamHTTPRequestHandle request) {
/* 141 */     return new SteamAPICall(SteamHTTPNative.sendHTTPRequest(this.isServer, this.callback, request.handle));
/*     */   }
/*     */   
/*     */   public SteamAPICall sendHTTPRequestAndStreamResponse(SteamHTTPRequestHandle request) {
/* 145 */     return new SteamAPICall(SteamHTTPNative.sendHTTPRequestAndStreamResponse(this.isServer, request.handle));
/*     */   }
/*     */   
/*     */   public int getHTTPResponseHeaderSize(SteamHTTPRequestHandle request, String headerName) {
/* 149 */     return SteamHTTPNative.getHTTPResponseHeaderSize(this.isServer, request.handle, headerName);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getHTTPResponseHeaderValue(SteamHTTPRequestHandle request, String headerName, ByteBuffer value) throws SteamException {
/* 155 */     if (!value.isDirect()) {
/* 156 */       throw new SteamException("Direct buffer required!");
/*     */     }
/*     */     
/* 159 */     return SteamHTTPNative.getHTTPResponseHeaderValue(this.isServer, request.handle, headerName, value, value
/* 160 */         .position(), value.remaining());
/*     */   }
/*     */   
/*     */   public int getHTTPResponseBodySize(SteamHTTPRequestHandle request) {
/* 164 */     return SteamHTTPNative.getHTTPResponseBodySize(this.isServer, request.handle);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean getHTTPResponseBodyData(SteamHTTPRequestHandle request, ByteBuffer data) throws SteamException {
/* 169 */     if (!data.isDirect()) {
/* 170 */       throw new SteamException("Direct buffer required!");
/*     */     }
/*     */     
/* 173 */     return SteamHTTPNative.getHTTPResponseBodyData(this.isServer, request.handle, data, data.position(), data.remaining());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getHTTPStreamingResponseBodyData(SteamHTTPRequestHandle request, int bodyDataOffset, ByteBuffer data) throws SteamException {
/* 179 */     if (!data.isDirect()) {
/* 180 */       throw new SteamException("Direct buffer required!");
/*     */     }
/*     */     
/* 183 */     return SteamHTTPNative.getHTTPStreamingResponseBodyData(this.isServer, request.handle, bodyDataOffset, data, data
/* 184 */         .position(), data.remaining());
/*     */   }
/*     */   
/*     */   public boolean releaseHTTPRequest(SteamHTTPRequestHandle request) {
/* 188 */     return SteamHTTPNative.releaseHTTPRequest(this.isServer, request.handle);
/*     */   }
/*     */ }


/* Location:              E:\代码\SlayTheSpire\desktop-1.0.jar!\com\codedisaster\steamworks\SteamHTTP.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */