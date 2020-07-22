/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.northwind.jpa.entity.response;

import java.util.Date;
import java.util.HashMap;
import org.springframework.http.HttpStatus;

/**
 *
 * @author ahza0
 */
public class ApiResponse {
    private HashMap<String,Object> meta;
    private Object data;

    public ApiResponse() {
        
    }

    public ApiResponse(HashMap<String, Object> meta, Object data) {
        this.meta = meta;
        this.data = data;
    }
    
    public ApiResponse(boolean success, HttpStatus status, String message, Object data) {       
        meta = new HashMap<>();
        meta.put("success", success);
        meta.put("status", status.value());
        meta.put("message", message);
        meta.put("timestamp", new Date().getTime());
        this.data = data;
    }
    
    public static ApiResponse ok(){
        return new ApiResponse(true, HttpStatus.OK, "OK", null);
    }
    
    public static ApiResponse ok(Object data){
        return new ApiResponse(true, HttpStatus.OK, "OK", data);
    }
    
    public static ApiResponse ok(String message){
        return new ApiResponse(true, HttpStatus.OK, message, null);
    } 
    
    public static ApiResponse ok(String message, Object data){
        return new ApiResponse(true, HttpStatus.OK, message, data);
    }
    
    public static ApiResponse created(){
        return new ApiResponse(true, HttpStatus.CREATED, "Created", null);
    }
    
    public static ApiResponse created(String message){
        return new ApiResponse(true, HttpStatus.CREATED, message, null);
    }
    
    public static ApiResponse created(String message, Object data){
        return new ApiResponse(true, HttpStatus.CREATED, message, data);
    }
    
    public static ApiResponse accepted(){
        return new ApiResponse(true, HttpStatus.ACCEPTED, "Accepted", null);
    }
    
    public static ApiResponse accepted(String message){
        return new ApiResponse(true, HttpStatus.ACCEPTED, message, null);
    }
    
    public static ApiResponse accepted(String message, Object data){
        return new ApiResponse(true, HttpStatus.ACCEPTED, message, data);
    }
    
    public static ApiResponse noContent(){
        return new ApiResponse(true, HttpStatus.NO_CONTENT, "No content!", null);
    }
    
    public static ApiResponse noContent(String message){
        return new ApiResponse(true, HttpStatus.NO_CONTENT, message, null);
    }
    
    public static ApiResponse noContent(String message, Object data){
        return new ApiResponse(true, HttpStatus.NO_CONTENT, message, data);
    }
    
    public static ApiResponse badRequest(){
        return new ApiResponse(false, HttpStatus.BAD_REQUEST, "bad request!", null);
    }
    
    public static ApiResponse badRequest(String message){
        return new ApiResponse(false, HttpStatus.BAD_REQUEST, message, null);
    }
    
    public static ApiResponse badRequest(String message, Object data){
        return new ApiResponse(false, HttpStatus.BAD_REQUEST, message, data);
    }
    
    public static ApiResponse unAuthorized(){
        return new ApiResponse(false, HttpStatus.UNAUTHORIZED, "Unauthorized!", null);
    }
    
    public static ApiResponse unAuthorized(String message){
        return new ApiResponse(false, HttpStatus.UNAUTHORIZED, message, null);
    }
    
    public static ApiResponse unAuthorized(String message, Object data){
        return new ApiResponse(false, HttpStatus.UNAUTHORIZED, message, data);
    }
    
    public static ApiResponse forbidden(){
        return new ApiResponse(false, HttpStatus.FORBIDDEN, "Forbidden!", null);
    }
    
    public static ApiResponse forbidden(String message){
        return new ApiResponse(false, HttpStatus.FORBIDDEN, message, null);
    }
    
    public static ApiResponse forbidden(String message, Object data){
        return new ApiResponse(false, HttpStatus.FORBIDDEN, message, data);
    }
    
    public static ApiResponse unProcessableEntity(){
        return new ApiResponse(false, HttpStatus.UNPROCESSABLE_ENTITY, "Unprocessable entity!", null);
    }
    
    public static ApiResponse unProcessableEntity(String message){
        return new ApiResponse(false, HttpStatus.UNPROCESSABLE_ENTITY, message, null);
    }
    
    public static ApiResponse unProcessableEntity(String message, Object data){
        return new ApiResponse(false, HttpStatus.UNPROCESSABLE_ENTITY, message, data);
    }
    
    public static ApiResponse notAcceptable(){
        return new ApiResponse(false, HttpStatus.NOT_ACCEPTABLE, "Not acceptable!", null);
    }
    
    public static ApiResponse notAcceptable(String message){
        return new ApiResponse(false, HttpStatus.NOT_ACCEPTABLE, message, null);
    }
    
    public static ApiResponse notAcceptable(String message, Object data){
        return new ApiResponse(false, HttpStatus.NOT_ACCEPTABLE, message, data);
    }
    
    public static ApiResponse conflict(){        
        return new ApiResponse(false, HttpStatus.CONFLICT, "Conflict!", null);
    }
    
    public static ApiResponse conflict(String message){
        return new ApiResponse(false, HttpStatus.CONFLICT, message, null);
    }
    
    public static ApiResponse conflict(String message, Object data){
        return new ApiResponse(false, HttpStatus.CONFLICT, message, data);
    }
    
    public static ApiResponse error(String message){
        return new ApiResponse(false, HttpStatus.NOT_ACCEPTABLE, message, null);
    }
    
    public static ApiResponse notFound(){        
        return new ApiResponse(false, HttpStatus.NOT_FOUND, "Data not found!", null);
    }
    
    public static ApiResponse notFound(String message){
        return new ApiResponse(false, HttpStatus.NOT_FOUND, message, null);
    }
    
    public static ApiResponse notFound(String message, Object data){
        return new ApiResponse(false, HttpStatus.NOT_FOUND, message, data);
    }
    
    public static ApiResponse internalServerError(){        
        return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error!", null);
    }
    
    public static ApiResponse internalServerError(String message){
        return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR, message, null);
    }
    
    public static ApiResponse internalServerError(String message, Object data){
        return new ApiResponse(false, HttpStatus.INTERNAL_SERVER_ERROR, message, data);
    }
    
    public static ApiResponse notImplemented(){        
        return new ApiResponse(false, HttpStatus.NOT_IMPLEMENTED, "Not implemented!", null);
    }
    
    public static ApiResponse notImplemented(String message){
        return new ApiResponse(false, HttpStatus.NOT_IMPLEMENTED, message, null);
    }
    
    public static ApiResponse notImplemented(String message, Object data){
        return new ApiResponse(false, HttpStatus.NOT_IMPLEMENTED, message, data);
    }
    
    public static ApiResponse unknownError(){        
        return new ApiResponse(false, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, "Unknown error!", null);
    }
    
    public static ApiResponse unknownError(String message){
        return new ApiResponse(false, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, message, null);
    }
    
    public static ApiResponse unknownError(String message, Object data){
        return new ApiResponse(false, HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, message, data);
    }

    public HashMap<String, Object> getMeta() {
        return meta;
    }

    public void setMeta(HashMap<String, Object> meta) {
        this.meta = meta;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
