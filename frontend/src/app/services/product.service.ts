import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Product } from '../common/product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private baseUrl: string = "http://localhost:8080/api/products";

  constructor(private httpClient: HttpClient) { }
  
  searchProducts(keyword: string): Observable<Product[]> {
    const searchUrl = `${this.baseUrl}/search?name=${keyword}`;
    return this.getProducts(searchUrl);
  }

  getProductList(categoryId: number): Observable<Product[]> {
    const searchUrl = `${this.baseUrl}/search?categoryId=${categoryId}`;
    return this.getProducts(searchUrl);
  }
  
  getProduct(productId: number): Observable<Product> {
    const searchUrl = `${this.baseUrl}/${productId}`;
    return this.httpClient.get<Product>(searchUrl);
  }

  private getProducts(searchUrl: string): Observable<Product[]> {
    return this.httpClient.get<Product[]>(searchUrl);
  }
}
