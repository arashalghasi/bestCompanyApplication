import { Component, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { toSignal } from '@angular/core/rxjs-interop';

interface Info {
  name: string;
  version: string;
  builtAt: string;
}

@Component({
  selector: 'app-root',
  template: `
    <h1>Demo app</h1>
    @if (info(); as info) {
      <p>{{ info.name }} <strong>{{ info.version }}</strong></p>
      <p>Built at {{ info.builtAt }}</p>
    } @else {
      <p>Loading backend info...</p>
    }
  `,
})
export class App {
  protected readonly info = toSignal(inject(HttpClient).get<Info>('/api/info'));
}
