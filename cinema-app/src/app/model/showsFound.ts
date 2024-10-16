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
 */
import { Outcome } from './outcome';
import { Show } from './show';

/**
 * List of shows found and outcome of the operation.
 */
export interface ShowsFound { 
    /**
     * List of shows found
     */
    showList?: Array<Show>;
    outcome: Outcome;
}