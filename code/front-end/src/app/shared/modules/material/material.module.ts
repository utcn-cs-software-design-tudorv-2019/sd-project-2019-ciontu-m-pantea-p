import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  MatButtonModule, MatCheckboxModule, MatDatepickerModule, MatExpansionModule, MatFormFieldModule,
  MatIconModule,
  MatListModule,
  MatMenuModule, MatNativeDateModule, MatOptionModule, MatProgressBarModule, MatProgressSpinnerModule, MatSelectModule,
  MatSidenavModule, MatSliderModule, MatSlideToggleModule, MatSnackBarModule, MatStepperModule,
  MatTabsModule,
  MatToolbarModule
} from '@angular/material';
import {FlexLayoutModule} from '@angular/flex-layout';

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    FlexLayoutModule,
    MatSidenavModule,
    MatToolbarModule,
    MatTabsModule,
    MatIconModule,
    MatButtonModule,
    MatListModule,
    MatMenuModule,
    MatStepperModule,
    MatFormFieldModule,
    MatOptionModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatDatepickerModule,
    // MatNativeDateModule,
    // MatMomentDateModule,
    MatCheckboxModule,
    MatProgressSpinnerModule,
    MatExpansionModule,
    MatProgressBarModule,
    MatSnackBarModule
  ],
  exports: [
    FlexLayoutModule,
    MatSidenavModule,
    MatToolbarModule,
    MatTabsModule,
    MatIconModule,
    MatButtonModule,
    MatListModule,
    MatMenuModule,
    MatStepperModule,
    MatFormFieldModule,
    MatOptionModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatDatepickerModule,
    // MatNativeDateModule,
    // MatMomentDateModule,
    MatCheckboxModule,
    MatProgressSpinnerModule,
    MatExpansionModule,
    MatProgressBarModule,
    MatSnackBarModule
  ]
})
export class MaterialModule { }
