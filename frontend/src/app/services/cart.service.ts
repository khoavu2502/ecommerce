import { Injectable } from '@angular/core';
import { CartItem } from '../common/cart-item';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartItems: CartItem[] = [];

  totalPrice: Subject<number> = new Subject<number>();
  totalQuantity: Subject<number> = new Subject<number>();

  constructor() { }

  addToCart(item: CartItem) {
    let alreadyExistsInCart: boolean = false;
    let existingCartItem: CartItem = undefined;

    if (this.cartItems.length > 0) {
      existingCartItem = this.cartItems.find(tempCartItemm => tempCartItemm.id === item.id);

      alreadyExistsInCart = (existingCartItem != undefined);

      if (alreadyExistsInCart) {
        existingCartItem.quantity++;
      } else {
        this.cartItems.push(item);
      }
    } else {
      this.cartItems.push(item);
    }

    this.computeCartTotal();
  }

  computeCartTotal() {
    let totalPriceValue: number = 0
    let totalQuantityValue: number = 0;

    for (let currentCartItem of this.cartItems) {
      totalPriceValue += currentCartItem.unitPrice * currentCartItem.quantity;
      totalQuantityValue += currentCartItem.quantity;
    }

    this.totalPrice.next(totalPriceValue);
    this.totalQuantity.next(totalQuantityValue);
  }
}
