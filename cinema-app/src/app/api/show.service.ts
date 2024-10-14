/**
 * Cinema multisala “CineMille”  - OpenAPI 3.0
 * This is a sample Cinema Server based on the OpenAPI 3.0 specification.  Users can search for shows scheduled for the next 3 weeks.   Cinema employees can look up archived shows, i.e., shows scheduled up to the day before the consultation
 *
 * OpenAPI spec version: 1.0.11
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 *//* tslint:disable:no-unused-variable member-ordering */

 import { Inject, Injectable, Optional }                      from '@angular/core';
 import { HttpClient, HttpHeaders, HttpParams,
          HttpResponse, HttpEvent }                           from '@angular/common/http';
 import { CustomHttpUrlEncodingCodec }                        from '../encoder';
 
 import { Observable }                                        from 'rxjs';
 
 import { ShowDate } from '../model/showDate';
 import { ShowsFound } from '../model/showsFound';
 
 import { BASE_PATH, COLLECTION_FORMATS }                     from '../variables';
 import { Configuration }                                     from '../configuration';
 
 
 @Injectable()
 export class ShowService {
 
     protected basePath = 'http://localhost:8080';
     public defaultHeaders = new HttpHeaders();
     public configuration = new Configuration();
 
     constructor(protected httpClient: HttpClient, @Optional()@Inject(BASE_PATH) basePath: string, @Optional() configuration: Configuration) {
         if (basePath) {
             this.basePath = basePath;
         }
         if (configuration) {
             this.configuration = configuration;
             this.basePath = basePath || configuration.basePath || this.basePath;
         }
     }
 
     /**
      * @param consumes string[] mime-types
      * @return true: consumes contains 'multipart/form-data', false: otherwise
      */
     private canConsumeForm(consumes: string[]): boolean {
         const form = 'multipart/form-data';
         for (const consume of consumes) {
             if (form === consume) {
                 return true;
             }
         }
         return false;
     }
 
 
     /**
      * Find cinema shows scheduled until yesterday
      * Return list of shows scheduled until yesterday
      * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
      * @param reportProgress flag to report request and response progress.
      */
     public getOldShows(observe?: 'body', reportProgress?: boolean): Observable<ShowsFound>;
     public getOldShows(observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ShowsFound>>;
     public getOldShows(observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ShowsFound>>;
     public getOldShows(observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
         let headers = this.defaultHeaders;
 
         // to determine the Accept header
         let httpHeaderAccepts: string[] = [
             'application/json',
             'application/xml'
         ];
         const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
         if (httpHeaderAcceptSelected != undefined) {
             headers = headers.set('Accept', httpHeaderAcceptSelected);
         }
 
         // to determine the Content-Type header
         const consumes: string[] = [
         ];
 
         return this.httpClient.request<ShowsFound>('get',`${this.basePath}/show/findOld`,
             {
                 withCredentials: this.configuration.withCredentials,
                 headers: headers,
                 observe: observe,
                 reportProgress: reportProgress
             }
         );
     }
 
     /**
      * Find cinema shows by dates (from - to)
      * Return list of shows scheduled in the specified time frame
      * @param showDateFrom Search shows from this date (Format yyyy-MM-dd)
      * @param showDateTo Search shows until this date ()Format yyyy-MM-dd)
      * @param observe set whether or not to return the data Observable as the body, response or events. defaults to returning the body.
      * @param reportProgress flag to report request and response progress.
      */
     public getShowsByFromTo(showDateFrom: ShowDate, showDateTo?: ShowDate, observe?: 'body', reportProgress?: boolean): Observable<ShowsFound>;
     public getShowsByFromTo(showDateFrom: ShowDate, showDateTo?: ShowDate, observe?: 'response', reportProgress?: boolean): Observable<HttpResponse<ShowsFound>>;
     public getShowsByFromTo(showDateFrom: ShowDate, showDateTo?: ShowDate, observe?: 'events', reportProgress?: boolean): Observable<HttpEvent<ShowsFound>>;
     public getShowsByFromTo(showDateFrom: ShowDate, showDateTo?: ShowDate, observe: any = 'body', reportProgress: boolean = false ): Observable<any> {
 
         if (showDateFrom === null || showDateFrom === undefined) {
             throw new Error('Required parameter showDateFrom was null or undefined when calling getShowsByFromTo.');
         }
 
 
         let queryParameters = new HttpParams({encoder: new CustomHttpUrlEncodingCodec()});
         if (showDateFrom !== undefined && showDateFrom !== null) {
             queryParameters = queryParameters.set('showDateFrom', <any>showDateFrom);
         }
         if (showDateTo !== undefined && showDateTo !== null) {
             queryParameters = queryParameters.set('showDateTo', <any>showDateTo);
         }
 
         let headers = this.defaultHeaders;
 
         // to determine the Accept header
         let httpHeaderAccepts: string[] = [
             'application/json',
             'application/xml'
         ];
         const httpHeaderAcceptSelected: string | undefined = this.configuration.selectHeaderAccept(httpHeaderAccepts);
         if (httpHeaderAcceptSelected != undefined) {
             headers = headers.set('Accept', httpHeaderAcceptSelected);
         }
 
         // to determine the Content-Type header
         const consumes: string[] = [
         ];
 
         return this.httpClient.request<ShowsFound>('get',`${this.basePath}/show/findByFromTo`,
             {
                 params: queryParameters,
                 withCredentials: this.configuration.withCredentials,
                 headers: headers,
                 observe: observe,
                 reportProgress: reportProgress
             }
         );
     }
 
 }
 