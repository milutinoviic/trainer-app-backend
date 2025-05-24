import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CertainReservationsComponent } from './certain-reservations.component';

describe('CertainReservationsComponent', () => {
  let component: CertainReservationsComponent;
  let fixture: ComponentFixture<CertainReservationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CertainReservationsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CertainReservationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
