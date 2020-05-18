import { IonicNativePlugin } from '@ionic-native/core';
import { Observable } from 'rxjs';
export declare type EventResponse = SomeEventResponse & OtherEventResponse & Error;
export interface SomeEventResponse {
    someId: string;
}
export interface OtherEventResponse {
    otherEvent: string;
}
/**
 * Loosened up with a dictionary notation, but all non-defined properties need to use (map['prop']) notation
 *
 * Ideally the developer would overload (merged declaration) this or create a new interface that would extend this one
 * so that he could specify any custom code without having to use array notation (map['prop']) for all of them.
 */
export declare class PluginDemoOriginal extends IonicNativePlugin {
    private _objectInstance;
    init(options: string): void;
    coolMethod(arg1: string): Promise<any>;
    on(event: string): Observable<EventResponse>;
}

export declare const PluginDemo: PluginDemoOriginal;