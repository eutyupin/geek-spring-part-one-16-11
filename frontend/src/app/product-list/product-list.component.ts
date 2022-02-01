import { Component, OnInit } from '@angular/core';
import {ProductService} from "../services/product.service";
import {Product} from "../model/product";
import {Router} from "@angular/router";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.less']
})
export class ProductListComponent implements OnInit {

  products: Product[] = [];
  constructor(private productService:ProductService,
              private router: Router) { }

  ngOnInit(): void {
    this.productService.findAll(1).subscribe(res => {
      this.products = res.content;
    }, err => {
      console.error(err);
    })
  }
}
